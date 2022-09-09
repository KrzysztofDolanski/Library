package com.epam.library.readers.exceptions;

import com.epam.library.readers.Reader;

import java.lang.module.FindException;
import java.util.function.Supplier;

public class ReaderNotFoundException extends FindException implements Supplier<Reader> {

    @Override
    public String getMessage() {
        return "Reader not found";
    }

    @Override
    public Reader get() {
        return new Reader();
    }
}
