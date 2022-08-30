package com.epam.library.books;

import java.util.Comparator;

public class BookByNameThenAuthorComparator implements Comparator<Book> {

    private static final Comparator<Book> COMPARATOR =
            Comparator.comparing(Book::getTitle)
                    .thenComparing(Book::getAuthor);

    @Override
    public int compare(Book book1, Book book2) {
        return COMPARATOR.compare(book1, book2);
    }
}
