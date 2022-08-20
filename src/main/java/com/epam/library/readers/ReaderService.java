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

    Reader create(String name, String surname, String email) {
        Reader reader = new Reader();
        reader.setName(name);
        reader.setSurname(surname);
        reader.setEmail(email);
        return readerRepository.create(reader);
    }

    String findById(long id) {
        return readerRepository.findById(id).toString();
    }


    void deleteById(long id) {
        readerRepository.deleteById(id);
    }
}
