package com.epam.library.readers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReaderServiceTest {

    @Autowired
    ReaderRepository repository;

    @Test
    void shouldFindReaderById(){
        //given
        repository.create(makeReader());
        repository.create(makeReader());
        repository.create(makeReader());
        repository.create(makeReader());
        repository.create(makeReader());
        Reader byId = repository.findById(5);
        //when
        long id = byId.getId();
        //then
        assertEquals(5,id);
    }

    @Test
    void shouldSaveReaderInDatabase(){
        //given
        repository.create(makeReader());
        Reader lastReader = repository.findLastReader();
        Reader reader = makeReader();
        long lastId = lastReader.getId();
        long lastIdAfterIncrement = ++lastId;

        //when
        assertNull(repository.findById(lastIdAfterIncrement).getName());
        repository.create(reader);

        //then
        assertNotNull(repository.findById(lastIdAfterIncrement));
    }

    @Test
    void shouldDeleteReaderById(){
        //given
        repository.create(makeReader());
        Reader lastReader = repository.findLastReader();
        //when
        Reader readerById = repository.findById(lastReader.getId());
        assertNotNull(readerById);
        assertEquals(lastReader, readerById);

        repository.deleteById(lastReader.getId());
        Reader deleted = repository.findById(lastReader.getId());
        //then

        assertNotEquals(lastReader, deleted);
    }

    private Reader makeReader() {
        Reader reader = new Reader();
        reader.setName("Jan");
        reader.setSurname("Kowalski");
        reader.setEmail("kowalski@kowalski.eu");
        return reader;
    }

    @AfterAll
    static void clearTable(){
        ReaderService readerService = new ReaderService(new ReaderRepository());
        readerService.deleteAllReaders();
    }
}
