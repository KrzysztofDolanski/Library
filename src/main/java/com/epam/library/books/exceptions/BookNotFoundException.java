package com.epam.library.books.exceptions;

import java.lang.module.FindException;

public class BookNotFoundException extends FindException {

    @Override
    public String getMessage() {
        return "Book not found";
    }

}
