package com.epam.library.readers.exceptions;

import com.epam.library.readers.Reader;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EmptyReadersDatabaseException extends FindException implements Supplier<List<Reader>> {

    @Override
    public List<Reader> get() {
        return new ArrayList<>();
    }
}
