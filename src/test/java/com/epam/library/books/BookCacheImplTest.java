package com.epam.library.books;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
class BookCacheImplTest {

    @MockBean
    private BookService bookService;
    @MockBean
    BookCookie bookCookie;
    @MockBean
    BookCacheImpl bookCache;

    void timeToFindBookShouldBeFasterAtSecondSearch() throws Exception {
        //given
        //when
        long startFirst = System.currentTimeMillis();
        bookCache.load(4);
        long endFirst = System.currentTimeMillis();
        long startSecond = System.currentTimeMillis();
        bookCache.load(4);
        long endSecond = System.currentTimeMillis();
        //then
        assertTrue((endFirst - startFirst) > (endSecond - startSecond));
    }

}