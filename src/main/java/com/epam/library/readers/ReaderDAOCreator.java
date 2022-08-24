package com.epam.library.readers;

import com.epam.library.database.DatabaseConnectionManager;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class ReaderDAOCreator {

    private final DatabaseConnectionManager dbConnector;

    public ReaderDAOCreator() {
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


    ReaderDAO getReaderDAO(){
        ReaderDAO readerDAO = null;
        try {
            readerDAO = new ReaderDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
        return readerDAO;
    }
}
