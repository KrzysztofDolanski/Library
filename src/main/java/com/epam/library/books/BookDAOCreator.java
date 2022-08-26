
package com.epam.library.books;

import com.epam.library.database.DatabaseConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class BookDAOCreator {
    private final DatabaseConnectionManager dbConnector;

    public BookDAOCreator() {
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


    BookDAO getReaderDAO(){
        BookDAO bookDAO = null;
        try {
            bookDAO = new BookDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
        return bookDAO;
    }
}
