package com.epam.library.books.functions;

import com.epam.library.books.Book;
import com.epam.library.books.BookDTO;

import java.util.function.Function;

public class BookToDTOFunction {

    private final Function<Book, BookDTO> function = b -> {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(b.getId());
        bookDTO.setTitle(b.getTitle());
        bookDTO.setAuthor(b.getAuthor());
        bookDTO.setAvailable(b.isAvailable());
        bookDTO.setReader(b.getReader());
        bookDTO.setDate(b.getRent_date());
        bookDTO.setTimes_of_borrowing(b.getTimes_of_borrowing());
        return bookDTO;
    };

    public BookDTO apply(Book book) {
        return function.apply(book);
    }
}
