package com.epam.library.books;

import com.epam.library.readers.ReaderRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;

    private static final String title = "Nasza Szkapa";

    @Test
    void shouldFindBookByTitle() {
        //given
        assertTrue(bookService.findByTitle(title).isEmpty());
        bookService.save(title, "author");
        //when
        List<BookDTO> byTitle = bookService.findByTitle(title);
        //then
        assertTrue(byTitle.stream().anyMatch(book -> book.getTitle().equals(title)));
    }

    @Test
    void shouldAddBookToDatabase() {
        //given
        String author = "Maria Konopnicka";
        //when
        assertTrue(bookService.findByTitle(title).isEmpty());
        bookService.save(title, author);
        //then
        assertEquals(bookService.findByTitle(title).get(0).getTitle(), title);
    }

    @Test
    void shouldDeleteBookFromDatabase() {
        //given
        String author = "Maria Konopnicka";
        bookService.save(title, author);
        assertEquals(bookService.findByTitle(title).get(0).getTitle(), title);
        //when
        bookService.deleteByTitleAuthorAndAvailability(title, author, true);
        //then
        assertTrue(bookService.findByTitle(title).isEmpty());
    }

    @AfterEach
    void removeFakeTitles() {
        BookService bookService1 = new BookService(new BookRepository(), new ReaderRepository());
        bookService1.deleteByTitle(title);
    }
}