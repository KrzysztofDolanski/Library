package com.epam.library.books;

import com.epam.library.database.DatabaseConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    private final DatabaseConnectionManager dbConnector;

    @Autowired
    public BookRepository() {
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


    public BookDTO findById(long id) {
        BookDAO bookDAO = null;
        try {
            bookDAO = new BookDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return BookMapperToDTO.mapToDTO(bookDAO.findById(id));
    }

    public BookDTO create(String title, String author) {
        Book book = new Book.Builder()
                .title(title)
                .author(author)
                .available(true)
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

    public void deleteById(long id) {
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


    public BookDTO update(BookDTO bookDTO){
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

    public BookDTO findLastBook() {
        try {
            BookDAO bookDAO = new BookDAO(dbConnector.getConnection());
            return BookMapperToDTO.mapToDTO(bookDAO.findLastBook());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    public List<BookDTO> findBookByTitle(String bookTitle) {
        try{
            BookDAO bookDAO = new BookDAO(dbConnector.getConnection());
            return bookDAO.findByTitle(bookTitle).stream().map(BookMapperToDTO::mapToDTO).collect(Collectors.toList());
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }
}
