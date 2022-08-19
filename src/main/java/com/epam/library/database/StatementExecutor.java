package com.epam.library.database;

import com.epam.library.books.Book;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class StatementExecutor {

    public String execute(String query) throws IOException, SQLException {
        var props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        ResultSet resultSet;
        StringBuilder sb = new StringBuilder();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                resultSet = stmt.executeQuery(query);
                while (resultSet.next()) {
                    statementProcedure(resultSet, query, sb);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return sb.toString();
    }

    private void statementProcedure(ResultSet resultSet, String query, StringBuilder sb) throws SQLException, IOException {

        String clear = "";
        if (query.charAt(query.length() - 1) == ';') {
            clear += query.substring(0, query.length() - 1);
        }

        String[] froms = clear.toLowerCase().split("from");
        String tableName = froms[1];
        switch (tableName.trim()) {
            case "books" -> {
                sb.append(resultSet.getInt(1)).append(" ");
                sb.append(resultSet.getString(2)).append(" ");
                sb.append(resultSet.getString(3));
            }
            case "readers" -> {
                sb.append(resultSet.getInt(1)).append(" ");
                sb.append(resultSet.getString(2)).append(" ");
                sb.append(resultSet.getString(3)).append(" ");
                sb.append(resultSet.getString(4)).append(" ");
                for (Book book : (List<Book>) resultSet.getObject(5)) {
                    sb.append(book.toString()).append("\n");
                }

            }
            default -> throw new SQLException("SQL statement does not fit");
        }

    }

}
