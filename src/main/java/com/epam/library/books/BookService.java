package com.epam.library.books;

import com.epam.library.readers.Reader;
import com.epam.library.readers.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    @Autowired
    BookService(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    BookDTO findById(Long bookId) {
        BookDTO bookDTO = null;
        try {
            bookDTO = bookRepository.findById(bookId);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return bookDTO;
    }

    List<BookDTO> findByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    BookDTO save(String title, String author) {
        return bookRepository.create(title, author);
    }

    void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    String borrow(Long readerId, String readerName, String readerSurname, String bookTitle) {
        Reader borrower;
        BookDTO bookDTO;
        if (readerId != null) {
            borrower = readerRepository.findById(readerId);
        } else {
            return "You must provide id.";
        }
        if (borrower.getName().equals(readerName) && borrower.getSurname().equals(readerSurname)) {
            bookDTO = bookRepository.findBookByTitle(bookTitle).stream().filter(BookDTO::isAvailable).findAny().orElseThrow();
            if (bookDTO.isAvailable()) {
                bookDTO.setReader(borrower);
                bookDTO.setAvailable(false);
                bookRepository.update(bookDTO);
            }
        } else {
            return "Readers values mismatch.";
        }

        return "Book " + bookDTO.getTitle() + " successful rent";
    }

    void deleteByTitleAuthorAndAvailability(String title, String author, boolean available) {
        bookRepository.deleteByTitleAuthorAvailable(title, author, available);
    }

    void deleteByTitle(String title) {
        bookRepository.deleteByTitle(title);
    }
}
