package com.epam.library.books.functions;

import com.epam.library.books.Book;
import com.epam.library.books.BookDTO;
import com.epam.library.books.functions.BookMapper;

import java.util.function.Function;

public class BookToDTOFunction implements Function<Book, BookDTO> {

    @Override
    public BookDTO apply(Book book) {
        BookMapper bookMapper = new BookMapper();
        return bookMapper.mapToDTO(book);
    }
}
