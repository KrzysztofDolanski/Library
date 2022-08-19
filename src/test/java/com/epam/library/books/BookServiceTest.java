package com.epam.library.books;

import com.epam.library.readers.ReaderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Mock
    BookService bookService;

    @Mock
    ReaderService readerService;

    @Test
    void shouldAddReaderToBookInDatabase() {
        //given
        //when
        //then
    }
}