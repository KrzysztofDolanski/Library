package com.epam.library.readers;

public class ReadersBooksNotReturnedException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Before reader can be deleted he must return all books";
    }
}
