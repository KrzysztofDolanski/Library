package com.epam.library.books;

import com.epam.library.books.exceptions.BookNotFoundException;
import com.epam.library.books.exceptions.SaveBookException;
import com.epam.library.readers.Reader;
import com.epam.library.readers.exceptions.ReaderNotFoundException;
import com.epam.library.readers.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final ReaderService readerService;

    @Autowired
    BookService(BookRepository bookRepository, ReaderService readerService) {
        this.bookRepository = bookRepository;
        this.readerService = readerService;
    }

    BookDTO findById(Long bookId) {
        BookDTO bookDTO = null;
        try {
            bookDTO = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return bookDTO;
    }

    List<BookDTO> findByTitle(String title) {
        return bookRepository.findBooksByTitle(title).stream().map(Optional::get).toList();
    }

    List<BookDTO> findByDate(String startDate, String endDate) {
        return bookRepository.findBooksByDate(startDate, endDate).stream().map(Optional::get).toList();
    }

    BookDTO save(BookDTO bookDTO) {
        return bookRepository.create(bookDTO).orElseThrow(SaveBookException::new);
    }

    void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    BookDTO borrow(Long readerId, String readerName, String readerSurname, String bookTitle) {
        Reader borrower = null;
        BookDTO bookDTO = null;
        if (readerId != null) {
            borrower = readerService.findById(readerId).orElseThrow(ReaderNotFoundException::new);
        } else {
            System.err.println("You must provide id.");
        }
        if (borrower != null) {
            if (borrower.getName().equals(readerName) && borrower.getSurname().equals(readerSurname)) {
                bookDTO = bookRepository.findBooksByTitle(bookTitle)
                        .stream()
                        .map(Optional::get)
                        .filter(BookDTO::isAvailable)
                        .findAny()
                        .orElseThrow();
                if (bookDTO.isAvailable()) {
                    bookDTO.setReader(borrower);
                    bookDTO.setAvailable(false);
                    bookRepository.update(bookDTO);
                }
            } else {
                System.err.println("Readers values mismatch.");
            }
        }
        return bookDTO;
    }

    void deleteByTitleAuthorAndAvailability(String title, String author, boolean available) {
        bookRepository.deleteByTitleAuthorAvailable(title, author, available);
    }

    void deleteByTitle(String title) {
        bookRepository.deleteByTitle(title);
    }

    void deleteAllBooks() {
        bookRepository.deleteAllBooks();
    }

    List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(Optional::get).toList();
    }

    String getAuthorByTitle(String title) {
        return bookRepository.getAuthorByTitle(title);
    }

    List<BookDTO> findByTitleAndAuthor(String title, String author) {
        return bookRepository.findBooksByTitleAndAuthor(title, author).stream().map(Optional::get).toList();
    }

}
