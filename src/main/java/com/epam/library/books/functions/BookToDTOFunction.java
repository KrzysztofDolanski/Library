package com.epam.library.books.functions;

import com.epam.library.books.Book;
import com.epam.library.books.BookDTO;

import java.util.function.Function;

public class BookToDTOFunction implements Function<Book, BookDTO> {

    private Function<Book, BookDTO> function = b -> new BookMapper().mapToDTO(b);

    @Override
    public BookDTO apply(Book book) {
        return function.apply(book);
    }
}
