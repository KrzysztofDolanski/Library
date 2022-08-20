package com.epam.library.readers;

import com.epam.library.books.Book;
import com.epam.library.database.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO extends DataAccessObject<Reader> {


    private static final String FIND_BY_ID = "SELECT r.id, r.name, r.surname, r.email, b.id From readers r LEFT JOIN books b on r.id=b.reader_id where r.id=?";

    private static final String SAVE_READER = "INSERT INTO readers(name, surname, email) " +
            "VALUES (?, ?, ?)";

    private static final String REMOVE_BY_ID = "DELETE FROM readers r WHERE r.id=?";

    public ReaderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Reader findById(long id) {
        Reader reader = new Reader();
        try (PreparedStatement statement = this.connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            long readerId = 0;
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                if (readerId == 0) {
                    reader.setId(resultSet.getLong(1));
                    readerId = reader.getId();
                    reader.setName(resultSet.getString(2));
                    reader.setSurname(resultSet.getString(3));
                    reader.setEmail(resultSet.getString(4));
                }
                Book book = new Book();
                book.setId(resultSet.getLong(5));
                book.setTitle(resultSet.getString(6));
                book.setAuthor(resultSet.getString(7));
                book.setAvailable(resultSet.getBoolean(8));
                book.setReader(reader);
                books.add(book);
            }
            reader.setBooks(books);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return reader;
    }

    @Override
    public List<Reader> findAll() {
        return null;
    }

    @Override
    public Reader update(Reader dto) {
        return null;
    }

    @Override
    public Reader create(Reader dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(SAVE_READER)){
            statement.setString(1, dto.getName());
            statement.setString(2, dto.getSurname());
            statement.setString(3, dto.getEmail());
            statement.execute();
            int id = this.getLastVal(READER_SEQUENCE);
            return this.findById(id);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteById(long id) {
        try(PreparedStatement statement = this.connection.prepareStatement(REMOVE_BY_ID)){
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
