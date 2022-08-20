package com.epam.library.books;

import com.epam.library.database.DataAccessObject;
import com.epam.library.readers.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends DataAccessObject<Book> {


    private static final String BOOK_LAST_ID = " books ORDER BY id DESC LIMIT 1";

    private static final String FIND_BY_ID = "SELECT b.id, b.title, b.author, b.available, r.id " +
            "FROM books b " +
            "LEFT JOIN readers r on b.reader_id=r.id " +
            "WHERE b.id=?";

    private static final String SAVE_BOOK = "INSERT INTO books(id, title, author, available) " +
            "VALUES (?, ?, ?, ?)";

    private static final String REMOVE_BY_ID = "DELETE FROM books b WHERE b.id=?";

    private static final String UPDATE_BOOK = "UPDATE books " +
            "SET id = ?, title = ?, author = ?, available = ?, reader_id=r.id " +
            "FROM books AS b " +
            "RIGHT JOIN readers AS r ON b.reader_id=r.id " +
            "WHERE books.id = ?" ;
//            "WITH sub AS(SELECT id, name, surname, email FROM readers) " +
//            "UPDATE books b " +
//            "SET b.id = ?, b.title = ?, b.author = ?, b.available = ?, b.reader_id = ? " +
//            "JOIN sub ON b.reader_id=sub.id " +
//            "WHERE b.id=?";

    private static final String FIND_ALL_BY_TITLE = "SELECT b.id, b.title, b.author, b.available, b.reader_id " +
            "FROM books b " +
            "WHERE b.title=?";


    public BookDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Book findById(long id) {
        Book book = new Book();
        try (PreparedStatement statement = this.connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            long bookId = 0;
            Reader reader = new Reader();
            while (resultSet.next()) {
                if (bookId == 0) {
                    book.setId(resultSet.getLong(1));
                    bookId = book.getId();
                    book.setTitle(resultSet.getString(2));
                    book.setAuthor(resultSet.getString(3));
                    book.setAvailable(resultSet.getBoolean(4));
                    reader.setId(resultSet.getLong(5));
                    reader.setName(resultSet.getString(6));
                    reader.setSurname(resultSet.getString(7));
                    reader.setEmail(resultSet.getString(8));
                    book.setReader(reader);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Book update(Book dto) {
        try (PreparedStatement statement = this.connection.prepareStatement(UPDATE_BOOK)) {
            statement.setLong(1, dto.getId());
            statement.setString(2, dto.getTitle());
            statement.setString(3, dto.getAuthor());
            statement.setBoolean(4, dto.isAvailable());
            statement.setLong(5, dto.getId());
            statement.execute();
            return this.findById(dto.getId());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }


    @Override
    public Book create(Book dto) {
        try (PreparedStatement statement = this.connection.prepareStatement(SAVE_BOOK)) {
            int id = this.getLastVal(BOOK_LAST_ID);
            statement.setLong(1, ++id);
            statement.setString(2, dto.getTitle());
            statement.setString(3, dto.getAuthor());
            statement.setBoolean(4, true);
            statement.execute();
            return this.findById(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteById(long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(REMOVE_BY_ID)) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    Book findLastBook() {
        int lastVal = this.getLastVal(BOOK_LAST_ID);
        return findById(lastVal);
    }

    List<Book> findByTitle(String bookTitle) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_BY_TITLE)) {
            statement.setString(1, bookTitle);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                Reader reader = new Reader();
                book.setId(resultSet.getLong(1));
                book.setTitle(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setAvailable(resultSet.getBoolean(4));

                if (!book.isAvailable()){
                reader.setId(resultSet.getLong(5));
                reader.setName(resultSet.getString(6));
                reader.setSurname(resultSet.getString(7));
                reader.setEmail(resultSet.getString(8));
                }


                book.setReader(reader);
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return books;
    }
}
