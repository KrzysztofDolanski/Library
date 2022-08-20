package com.epam.library.database;

import com.epam.library.readers.Reader;
import com.epam.library.readers.ReaderDAO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataAccessObjectTest {

    DatabaseConnectionManager dbConnector;
    private static final String READER_LAST_ID = " readers ORDER BY id DESC LIMIT 1";

    private DataAccessObjectTest() {
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

    @Test
    void shouldIncreaseId() throws SQLException {
        //given
        ReaderDAO readerDAO = new ReaderDAO(dbConnector.getConnection());
        Reader reader = makeReader();
        int lastVal = readerDAO.getLastVal(READER_LAST_ID);

        //when
        readerDAO.create(reader);
        int incrementVal = readerDAO.getLastVal(READER_LAST_ID);

        //then
        assertEquals(++lastVal, incrementVal);
    }

    private Reader makeReader() {
        Reader reader = new Reader();
        reader.setName("Jan");
        reader.setSurname("Kowalski");
        reader.setEmail("kowalski@kowalski.eu");
        return reader;
    }

}