package com.epam.library.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

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

    Reader findById(long id) {
        return readerRepository.findById(id);
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

    List<Reader> findByNameAndSurname(String readerName, String readerSurname) {
        return readerRepository.findByNameAndSurname(readerName, readerSurname);
    }

}
