package com.epam.library.readers.exceptions;

import com.epam.library.readers.Reader;

import java.sql.BatchUpdateException;
import java.util.function.Supplier;

public class ReaderUpdateException extends BatchUpdateException implements Supplier<Reader> {
    @Override
    public String getMessage() {
        return super.getMessage() + " Reader update process unsuccessful.";
    }

    @Override
    public Reader get() {
        return new Reader();
    }
}
