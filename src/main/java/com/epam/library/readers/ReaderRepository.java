package com.epam.library.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Cookie;
import java.util.List;

@Repository
public class ReaderRepository {


    private final ReaderDAOCreator readerDAOCreator;

    @Autowired
    public ReaderRepository(ReaderDAOCreator readerDAOCreator) {
        this.readerDAOCreator = readerDAOCreator;
    }

    public Reader findById(long id) {
        return readerDAOCreator.getReaderDAO().findById(id);
    }

    Reader create(String name, String surname, String email) {
        Reader reader = new Reader();
        reader.setName(name);
        reader.setSurname(surname);
        reader.setEmail(email);
        return readerDAOCreator.getReaderDAO().create(reader);
    }

    void deleteById(long id) {
        readerDAOCreator.getReaderDAO().deleteById(id);
    }

    Reader findLastReader() {
        return readerDAOCreator.getReaderDAO().findLastReader();
    }

    void deleteAllReaders() {
        readerDAOCreator.getReaderDAO().deleteAllReaders();
    }

    Reader update(Reader reader) {
        return readerDAOCreator.getReaderDAO().update(reader);
    }

    List<Reader> findByNameAndSurname(String readerName, String readerSurname) {
        return readerDAOCreator.getReaderDAO().findByNameAndSurname(readerName, readerSurname);
    }

}

