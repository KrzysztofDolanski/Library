package com.epam.library.books.functions;

import com.epam.library.books.Book;
import com.epam.library.books.BookDTO;

import java.util.function.Function;

public class BookDTOToBookFunction {

    private final Function<BookDTO, Book> function = bookDTO -> {
        return new Book.Builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .available(bookDTO.isAvailable())
                .reader(bookDTO.getReader())
                .date(bookDTO.getDate())
                .timesOfBorrowing(bookDTO.getTimes_of_borrowing())
                .build();
    };

    public Book apply(BookDTO bookDTO) {
        return function.apply(bookDTO);
    }
}
