package com.epam.library.readers;

import com.epam.library.books.Book;
import com.epam.library.database.DataAccessObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDAO extends DataAccessObject<Reader> {

    private static final String READER_LAST_ID = " readers ORDER BY id DESC LIMIT 1";

    private static final String FIND_BY_ID = "SELECT r.id, r.name, r.surname, r.email, b.id " +
            "FROM readers r " +
            "LEFT JOIN books b on r.id=b.reader_id " +
            "WHERE r.id=?";

    private static final String SAVE_READER = "INSERT INTO readers(id, name, surname, email) " +
            "VALUES (?, ?, ?, ?)";

    private static final String REMOVE_BY_ID = "DELETE FROM readers r WHERE r.id=?";
    private static final String REMOVE_ALL = "DELETE FROM readers";

    protected ReaderDAO(Connection connection) {
        super(connection);
    }

    @Override
    protected Reader findById(long id) {
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
    protected List<Reader> findAll() {
        return null;
    }

    @Override
    protected Reader update(Reader dto) {
        return null;
    }

    @Override
    protected Reader create(Reader dto) {
        try (PreparedStatement statement = this.connection.prepareStatement(SAVE_READER)) {
            int id = this.getLastVal(READER_LAST_ID);
            statement.setLong(1, ++id);
            statement.setString(2, dto.getName());
            statement.setString(3, dto.getSurname());
            statement.setString(4, dto.getEmail());
            statement.execute();
            return this.findById(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    protected void deleteById(long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(REMOVE_BY_ID)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    Reader findLastReader() {
        int lastVal = this.getLastVal(READER_LAST_ID);
        return findById(lastVal);
    }

    public void deleteAllReaders() {
        try (PreparedStatement statement = this.connection.prepareStatement(REMOVE_ALL)) {
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
