package com.epam.library.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

    public static volatile DatabaseConnectionManager instance;
    private final String url;
    private final Properties properties;

    private DatabaseConnectionManager(String host, String databaseName, String username, String password) {
        this.url = "jdbc:postgresql://" + host + "/" + databaseName;
        properties = new Properties();
        this.properties.setProperty("username", username);
        this.properties.setProperty("password", password);
    }

    /**
     * Get access to database with given values form properties.
     *
     * @return singleton instance of DatabaseConnectionManager
     * @throws IOException
     */
    public DatabaseConnectionManager getInstance() throws IOException {
        var props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("/application.properties"));
        DatabaseConnectionManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized (DatabaseConnectionManager.class) {
            if (instance == null) {
                instance = new DatabaseConnectionManager(
                        props.getProperty("db.host"),
                        props.getProperty("db.name"),
                        props.getProperty("db.username"),
                        props.getProperty("db.password"));
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.properties);
    }
}
