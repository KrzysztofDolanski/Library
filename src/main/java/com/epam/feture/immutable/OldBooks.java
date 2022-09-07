package com.epam.feture.immutable;

import com.epam.library.books.Book;

import java.util.ArrayList;
import java.util.List;

public final class OldBooks {


    private final String name;
    private final List<Book> oldBooks;


    public OldBooks(String name, List<Book> oldBooks) {
        this.name = name;
        this.oldBooks = oldBooks;
    }


    public String getName() {
        String copy = name;
        return copy;
    }

    public List<Book> getOldBooks() {
        List<Book> copy = new ArrayList<>(oldBooks);
        for (Book oldBook : oldBooks) {
            Book book = new Book.Builder()
                    .id(oldBook.getId())
                    .title(oldBook.getTitle())
                    .author(oldBook.getAuthor())
                    .available(oldBook.isAvailable())
                    .reader(oldBook.getReader())
                    .date(oldBook.getRent_date())
                    .build();
            copy.add(book);
        }
        return copy;
    }
}
