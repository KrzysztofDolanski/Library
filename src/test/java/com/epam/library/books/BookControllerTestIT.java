package com.epam.library.books;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookControllerTestIT {

    @Mock
    CacheManager cacheManager;

    BookDAO bookDAO;

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

    private Optional<String> getCachedBook(String title) {
        return ofNullable(cacheManager.getCache("author")).map(c -> c.get(title, String.class));
    }

    @BeforeEach
    void saveBooks() throws SQLException {
        bookDAO = new BookDAO(getConnection());
        Book book = new Book();
        String title = "Gold";
        String author = "Anthony Hopkins";
        book.setTitle(title);
        book.setAuthor(author);
        bookDAO.create(book);
    }

    @Test
    void shouldFindBookInCache() {
        //given
        String title = "Gold";
        String byTitle = bookDAO.getAuthorsByTitle(title).stream().findFirst().orElseThrow();

        String cachedBook = "";
        //when
        if (getCachedBook(title).isPresent()) {
            cachedBook += getCachedBook(title).orElseThrow();
        }
        //then
        assertEquals(byTitle, cachedBook);
    }

}
