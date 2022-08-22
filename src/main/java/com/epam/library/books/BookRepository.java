package com.epam.library.books;

import com.epam.library.database.DatabaseConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    private final DatabaseConnectionManager dbConnector;

    @Autowired
    BookRepository() {
        var props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        dbConnector = new DatabaseConnectionManager(
                props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password"));
    }


    BookDTO findById(long id) {
        BookDAO bookDAO = null;
        try {
            bookDAO = new BookDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return BookMapperToDTO.mapToDTO(bookDAO.findById(id));
    }

    BookDTO create(String title, String author) {
        Book book = new Book.Builder()
                .title(title)
                .author(author)
                .available(true)
                .date(Date.from(Instant.ofEpochSecond(LocalDateTime.now().getSecond())))
                .build();
        BookDAO bookDAO = null;
        try {
            bookDAO = new BookDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
        return BookMapperToDTO.mapToDTO(bookDAO.create(book));
    }

    void deleteById(long id) {
        BookDAO bookDAO = null;
        try {
            bookDAO = new BookDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if (bookDAO != null) {
            bookDAO.deleteById(id);
        }
    }


    BookDTO update(BookDTO bookDTO) {
        BookDAO bookDAO = null;
        BookDTO updatedBook = null;
        try {
            bookDAO = new BookDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if (bookDAO != null) {
            Book book = BookMapperToDTO.mapToBook(bookDTO);
            Book update = bookDAO.update(book);
            updatedBook = BookMapperToDTO.mapToDTO(update);
        }
        return updatedBook;
    }

    List<BookDTO> findBooksByTitle(String bookTitle) {
        try {
            BookDAO bookDAO = new BookDAO(dbConnector.getConnection());
            return bookDAO.findByTitle(bookTitle).stream().map(BookMapperToDTO::mapToDTO).collect(Collectors.toList());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }


    List<BookDTO> findBooksByDate(String startDate, String endDate){
        try {
            BookDAO bookDAO = new BookDAO(dbConnector.getConnection());
            return bookDAO.findByDate(startDate, endDate).stream().map(BookMapperToDTO::mapToDTO).collect(Collectors.toList());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    void deleteByTitleAuthorAvailable(String title, String author, boolean available) {
        try {
            BookDAO bookDAO = new BookDAO(dbConnector.getConnection());
            bookDAO.deleteByTitleAuthorAvailable(title, author, available);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    void deleteByTitle(String title) {
        BookDAO bookDAO = null;
        try {
            bookDAO = new BookDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if (bookDAO != null) {
            bookDAO.deleteByTitle(title);
        }
    }

    void deleteAllBooks() {
        BookDAO bookDAO = null;
        try {
            bookDAO = new BookDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if (bookDAO != null) {
            bookDAO.deleteAllBooks();
        }
    }
}
