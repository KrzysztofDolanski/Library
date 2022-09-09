package com.epam.library.books.exceptions;

import com.epam.library.books.Book;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class EmptyBooksDatabaseException extends FindException implements Supplier<List<Book>> {
    @Override
    public List<Book> get() {
        return new ArrayList<>();
    }
}
