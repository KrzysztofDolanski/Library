package com.epam.library.readers;

import com.epam.library.database.DatabaseConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Repository
public class ReaderRepository {

    private final DatabaseConnectionManager dbConnector;

    @Autowired
    public ReaderRepository() {
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


    public Reader findById(long id) {
        ReaderDAO readerDAO = null;
        try {
            readerDAO = new ReaderDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return readerDAO.findById(id);
    }

    Reader create(Reader reader) {
        ReaderDAO readerDAO = null;
        try {
            readerDAO = new ReaderDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
        return readerDAO.create(reader);
    }

    void deleteById(long id) {
        ReaderDAO readerDAO = null;
        try {
            readerDAO = new ReaderDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if (readerDAO != null) {
            readerDAO.deleteById(id);
        }
    }

    Reader findLastReader() {
        try {
            ReaderDAO readerDAO = new ReaderDAO(dbConnector.getConnection());
            return readerDAO.findLastReader();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    void deleteAllReaders() {
        ReaderDAO readerDAO = null;
        try {
            readerDAO = new ReaderDAO(dbConnector.getConnection());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if (readerDAO != null) {
            readerDAO.deleteAllReaders();
        }
    }

    Reader update(Reader reader) {
        try {
            ReaderDAO readerDAO = new ReaderDAO(dbConnector.getConnection());
            return readerDAO.update(reader);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }
}

