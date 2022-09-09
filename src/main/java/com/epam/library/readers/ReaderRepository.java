package com.epam.library.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReaderRepository {


    private final ReaderDAOCreator readerDAOCreator;

    @Autowired
    public ReaderRepository(ReaderDAOCreator readerDAOCreator) {
        this.readerDAOCreator = readerDAOCreator;
    }

    Optional<Reader> findById(long id) {
        return Optional.of(readerDAOCreator.getReaderDAO().findById(id));
    }

    Reader create(Reader reader) {
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

    Optional<Reader> update(Reader reader) {
        return Optional.of(readerDAOCreator.getReaderDAO().update(reader));
    }

    List<Reader> findByNameAndSurname(String readerName, String readerSurname) {
        return readerDAOCreator.getReaderDAO().findByNameAndSurname(readerName, readerSurname);
    }

    public List<String> findReadersBooks(long readerId) {
        return readerDAOCreator.getReaderDAO().findBooksByReaderId(readerId);
    }

    public void giveBackBook(Reader reader, long bookId) {
        readerDAOCreator.getReaderDAO().giveBackBook(reader, bookId);
    }
}

