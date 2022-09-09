package com.epam.library.books.exceptions;

import com.epam.library.books.BookDTO;

import java.lang.module.FindException;
import java.util.function.Supplier;

public class SaveBookException extends FindException implements Supplier<BookDTO> {
    @Override
    public BookDTO get() {
        return new BookDTO();
    }
}
