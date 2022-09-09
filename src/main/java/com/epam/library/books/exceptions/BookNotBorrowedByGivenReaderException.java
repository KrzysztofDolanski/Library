package com.epam.library.books.exceptions;

import java.lang.module.FindException;

public class BookNotBorrowedByGivenReaderException extends FindException {

    Long bookId;
    Long readerId;

    @Override
    public String getMessage() {
        return "Reader id:" + this.readerId + " not borrow the book with id:" + this.bookId;
    }

    public BookNotBorrowedByGivenReaderException(long bookId, Long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
    }
}
