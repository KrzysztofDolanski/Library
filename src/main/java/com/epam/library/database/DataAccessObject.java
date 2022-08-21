package com.epam.library.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class DataAccessObject<T extends DataTransferObject> {

    protected static final String LAST_VAL = "SELECT id from ";
    protected final Connection connection;

    protected DataAccessObject(Connection connection) {
        super();
        this.connection = connection;
    }

    protected abstract T findById(long id);

    protected abstract List<T> findAll();

    protected abstract T update(T dto);

    protected abstract T create(T dto);

    protected abstract void deleteById(long id);

    protected int getLastVal(String sequence){
        int key = 0;
        String sql = LAST_VAL + sequence;
        try(Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                key=rs.getInt(1);
            }
            return key;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
