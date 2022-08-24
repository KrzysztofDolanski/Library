package com.epam.library.books;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BookDAOTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    private static final String title = "Nasza Szkapa";

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "sa");
        connectionProps.put("password", "");

        conn = DriverManager.getConnection(
                "jdbc:h2:mem:testdb",
                connectionProps);
        System.out.println("Connected to database");
        return conn;
    }


    @Test
    void shouldFindBookByTitle() {
        //given
        BookDAO bookDAO;
        try {
            bookDAO = new BookDAO(getConnection());
            jdbcTemplate.execute(
                    "CREATE TABLE IF NOT EXISTS readers(id SERIAL PRIMARY KEY, name VARCHAR(250), surname VARCHAR(250), email VARCHAR(200));\n" +
                            "CREATE TABLE IF NOT EXISTS books(id SERIAL, title VARCHAR(250), author VARCHAR(250), available BOOLEAN, reader_id INTEGER, rent_date DATE NOT NULL DEFAULT CURRENT_DATE, PRIMARY KEY (id), CONSTRAINT fk_reader FOREIGN KEY (reader_id) REFERENCES readers(id));");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }

        assertTrue(bookDAO.findByTitle(title).isEmpty());
        Book book = new Book.Builder().title(title).author("Sienkiewicz").build();
        bookDAO.create(book);
        //when
        List<BookDTO> byTitle = bookDAO.findByTitle(title).stream().map(BookMapper::mapToDTO).toList();
        //then
        assertTrue(byTitle.stream().anyMatch(entity -> entity.getTitle().equals(title)));
    }

    @Test
    void shouldAddBookToDatabase() {
        //given
        String author = "Maria Konopnicka";
        BookDAO bookDAO;
        try {
            bookDAO = new BookDAO(getConnection());
            jdbcTemplate.execute(
                    "CREATE TABLE IF NOT EXISTS readers(id SERIAL PRIMARY KEY, name VARCHAR(250), surname VARCHAR(250), email VARCHAR(200));\n" +
                            "CREATE TABLE IF NOT EXISTS books(id SERIAL, title VARCHAR(250), author VARCHAR(250), available BOOLEAN, reader_id INTEGER, rent_date DATE NOT NULL DEFAULT CURRENT_DATE, PRIMARY KEY (id), CONSTRAINT fk_reader FOREIGN KEY (reader_id) REFERENCES readers(id));");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        //when
        assertTrue(bookDAO.findByTitle(title).isEmpty());
        Book book = new Book.Builder().title(title).author(author).build();
        bookDAO.create(book);
        //then
        assertEquals(bookDAO.findByTitle(title).get(0).getTitle(), title);
    }

    @Test
    void shouldDeleteBookFromDatabase() {
        //given
        String author = "Maria Konopnicka";
        BookDAO bookDAO;
        try {
            bookDAO = new BookDAO(getConnection());
            jdbcTemplate.execute(
                    "CREATE TABLE IF NOT EXISTS readers(id SERIAL PRIMARY KEY, name VARCHAR(250), surname VARCHAR(250), email VARCHAR(200));\n" +
                            "CREATE TABLE IF NOT EXISTS books(id SERIAL, title VARCHAR(250), author VARCHAR(250), available BOOLEAN, reader_id INTEGER, rent_date DATE NOT NULL DEFAULT CURRENT_DATE, PRIMARY KEY (id), CONSTRAINT fk_reader FOREIGN KEY (reader_id) REFERENCES readers(id));");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        Book book = new Book.Builder().title(title).author(author).build();
        bookDAO.create(book);
        assertEquals(bookDAO.findByTitle(title).get(0).getTitle(), title);
        //when
        bookDAO.deleteByTitleAuthorAvailable(title, author, true);
        //then
        assertTrue(bookDAO.findByTitle(title).isEmpty());
    }

    @AfterEach
    void removeFakeTitles() {
        BookDAO bookDAO;
        try {
            bookDAO = new BookDAO(getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        bookDAO.deleteByTitle(title);
    }
}