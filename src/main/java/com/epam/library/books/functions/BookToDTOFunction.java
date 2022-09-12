package com.epam.library.books.functions;

import com.epam.library.books.Book;
import com.epam.library.books.BookDTO;

import java.util.function.Function;

public class BookToDTOFunction {

    private final Function<Book, BookDTO> function = b -> new BookMapper().mapToDTO(b);

    public BookDTO apply(Book book) {
        return function.apply(book);
    }
}
