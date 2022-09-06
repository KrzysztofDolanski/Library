package com.epam.library.books;

import com.epam.library.cache.Cache;

import java.util.Optional;

public interface BookCache extends Cache {

    Optional<BookDTO> get(long bookId);
    BookDTO getBookDB(long bookId);
}
