package com.epam.library.books;

import com.epam.library.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class BookCacheImpl implements Cache {

    private final BookService service;

    private List<BookDTO> bookDTOCache;

    @Autowired
    public BookCacheImpl(BookService service) {
        bookDTOCache = new ArrayList<>();
        this.service = service;
    }

    @Override
    public BookDTO load(long id) {
        BookDTO bookDTO;
        if (get(id).isPresent()) {
            return get(id).orElseThrow();
        } else {
            bookDTO = getBookDB(id);
            bookDTOCache.add(bookDTO);
        }
        return bookDTO;
    }
    private Optional<BookDTO> get(long bookId) {
        Stream<BookDTO> bookDTOStream = this.bookDTOCache.stream()
                .filter(orderDetails -> orderDetails.getId().equals(bookId));
        return bookDTOStream.findFirst();
    }

    private BookDTO getBookDB(long bookId) {
        return service.findById(bookId);
    }

    public void cleanCache() {
        bookDTOCache.clear();
    }
}
