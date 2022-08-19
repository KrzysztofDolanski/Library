package com.epam.library.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReaderService {


    private final ReaderRepository readerRepository;

    @Autowired
    ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    void save(String name, String surname, String email) {
        Reader reader = new Reader();
        reader.setName(name);
        reader.setSurname(surname);
        reader.setEmail(email);
        readerRepository.save(reader);
    }

    String findById(long id) {
        return readerRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)).toString();
    }


    void deleteById(long id) {
        readerRepository.deleteById(id);
    }
}
