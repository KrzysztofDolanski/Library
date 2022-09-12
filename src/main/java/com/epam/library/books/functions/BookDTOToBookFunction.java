package com.epam.library.books.functions;

import com.epam.library.books.Book;
import com.epam.library.books.BookDTO;

import java.util.function.Function;

public class BookDTOToBookFunction implements Function<BookDTO, Book> {

    @Override
    public Book apply(BookDTO bookDTO) {
        BookMapper bookMapper = new BookMapper();
        return bookMapper.mapToBook(bookDTO);
    }
}
