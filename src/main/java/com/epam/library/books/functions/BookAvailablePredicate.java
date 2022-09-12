package com.epam.library.books.functions;

import com.epam.library.books.Book;

import java.util.function.Predicate;

public class BookAvailablePredicate {

    Predicate<Book> predicate = Book::isAvailable;

    public boolean test(Book book){
        return predicate.test(book);
    }
}
