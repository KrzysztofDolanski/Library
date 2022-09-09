package com.epam.library.books;

import com.epam.library.database.DataAccessObject;
import com.epam.library.readers.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends DataAccessObject<Book> {


    private static final String BOOK_LAST_ID = " books ORDER BY id DESC LIMIT 1";

    private static final String SAVE_BOOK = "INSERT INTO books(id, title, author, available, rent_date) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String REMOVE_BY_ID = "DELETE FROM books b WHERE b.id=?";
    private static final String REMOVE_BY_TITLE = "DELETE FROM books b WHERE b.title=?";

    private static final String UPDATE_BOOK = "UPDATE books " +
            "SET id = ?, title = ?, author = ?, available = ?, reader_id=r.id, rent_date = CURRENT_DATE " +
            "FROM books AS b " +
            "RIGHT JOIN readers AS r ON b.reader_id=r.id " +
            "WHERE books.id = ?";

    private static final String FIND_BY_ID = "SELECT b.id, b.title, b.author, b.available, r.id, r.name, r.surname, r.email, b.rent_date " +
            "FROM books b " +
            "LEFT JOIN readers r on b.reader_id=r.id " +
            "WHERE b.id=?";
    private static final String FIND_ALL_BY_TITLE = "SELECT b.id, b.title, b.author, b.available, r.id, r.name, r.surname, r.email, b.rent_date " +
            "FROM books b " +
            "LEFT JOIN readers r on b.reader_id=r.id " +
            "WHERE b.title=?";

    private static final String FIND_ALL_BY_DATE = "SELECT b.id, b.title, b.author, b.available, r.id, r.name, r.surname, r.email, b.rent_date " +
            "FROM books b " +
            "LEFT JOIN readers r on b.reader_id=r.id " +
            "WHERE rent_date BETWEEN ? AND ?";
    private static final String REMOVE_BY_TITLE_AUTHOR_AVAILABILITY = "DELETE FROM books " +
            "WHERE id IN ( " +
            "SELECT id FROM " +
            "books WHERE title = ? AND author = ? and available = ? LIMIT 1)";
    private static final String REMOVE_ALL_BOOKS = "DELETE FROM books";
    private static final String FIND_ALL_BOOKS = "SELECT b.id, b.title, b.author, b.available, r.id, r.name, r.surname, r.email, b.rent_date " +
            "FROM books b " +
            "LEFT JOIN readers r on b.reader_id=r.id ";
    private static final String FIND_ALL_AUTHORS_BY_TITLE = "SELECT b.author FROM books b WHERE b.title=?";
    private static final String FIND_ALL_BY_TITLE_AND_AUTHOR = "SELECT b.id, b.title, b.author, b.available, r.id, r.name, r.surname, r.email, b.rent_date " +
            "FROM books b " +
            "LEFT JOIN readers r on b.reader_id=r.id " +
            "WHERE b.title=? " +
            "AND b.author=?";;

    private final BookByNameThenAuthorComparator bookByNameThenAuthorComparator;
    BookDAO(Connection connection) {
        super(connection);
        bookByNameThenAuthorComparator = new BookByNameThenAuthorComparator();
    }

    @Override
    protected Book findById(long id) {
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

                    book.setRent_date(resultSet.getDate(9));
                    book.setReader(reader);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return book;
    }

    @Override
    protected List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_BOOKS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                Reader reader = new Reader();
                book.setId(resultSet.getLong(1));
                book.setTitle(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setAvailable(resultSet.getBoolean(4));

                if (!book.isAvailable()) {
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
        return books.stream().sorted(bookByNameThenAuthorComparator).toList();
    }

    @Override
    protected Book update(Book dto) {
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
    protected Book create(Book dto) {
        try (PreparedStatement statement = this.connection.prepareStatement(SAVE_BOOK)) {
            int id = this.getLastVal(BOOK_LAST_ID);
            statement.setLong(1, ++id);
            statement.setString(2, dto.getTitle());
            statement.setString(3, dto.getAuthor());
            statement.setBoolean(4, true);
            statement.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
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

                if (!book.isAvailable()) {
                    reader.setId(resultSet.getLong(5));
                    reader.setName(resultSet.getString(6));
                    reader.setSurname(resultSet.getString(7));
                    reader.setEmail(resultSet.getString(8));
                }
                book.setRent_date(resultSet.getDate(9));
                book.setReader(reader);
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return books;
    }


    List<Book> findByTitleAndAuthor(String bookTitle, String author) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_BY_TITLE_AND_AUTHOR)) {
            statement.setString(1, bookTitle);
            statement.setString(2, author);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                Reader reader = new Reader();
                book.setId(resultSet.getLong(1));
                book.setTitle(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setAvailable(resultSet.getBoolean(4));

                if (!book.isAvailable()) {
                    reader.setId(resultSet.getLong(5));
                    reader.setName(resultSet.getString(6));
                    reader.setSurname(resultSet.getString(7));
                    reader.setEmail(resultSet.getString(8));
                }
                book.setRent_date(resultSet.getDate(9));
                book.setReader(reader);
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return books;
    }


    List<Book> findByDate(String startDate, String endDate) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_BY_DATE)) {
            statement.setDate(1, java.sql.Date.valueOf(startDate));
            statement.setDate(2, java.sql.Date.valueOf(endDate));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                Reader reader = new Reader();
                book.setId(resultSet.getLong(1));
                book.setTitle(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setAvailable(resultSet.getBoolean(4));

                if (!book.isAvailable()) {
                    reader.setId(resultSet.getLong(5));
                    reader.setName(resultSet.getString(6));
                    reader.setSurname(resultSet.getString(7));
                    reader.setEmail(resultSet.getString(8));
                }
                book.setReader(reader);
                book.setRent_date(resultSet.getDate(9));
                books.add(book);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return books;
    }


    void deleteByTitleAuthorAvailable(String title, String author, boolean available) {
        try (PreparedStatement statement = this.connection.prepareStatement(REMOVE_BY_TITLE_AUTHOR_AVAILABILITY)) {
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setBoolean(3, available);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    void deleteByTitle(String title) {
        try (PreparedStatement statement = this.connection.prepareStatement(REMOVE_BY_TITLE)) {
            statement.setString(1, title);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    void deleteAllBooks() {
        try (PreparedStatement statement = this.connection.prepareStatement(REMOVE_ALL_BOOKS)) {
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<String> getAuthorsByTitle(String title) {
        List<String> authors = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(FIND_ALL_AUTHORS_BY_TITLE)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authors.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return authors;
    }
}
