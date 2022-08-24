package com.epam.library.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    @Autowired
    ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    Reader create(Reader reader) {
        return readerRepository.create(reader);
    }

    String findById(long id) {
        return readerRepository.findById(id).toString();
    }


    void deleteById(long id) {
        readerRepository.deleteById(id);
    }

    Reader findLastReader() {
        return readerRepository.findLastReader();
    }

    void deleteAllReaders() {
        readerRepository.deleteAllReaders();
    }

    Reader update(Reader reader) {
        return readerRepository.update(reader);
    }
}
