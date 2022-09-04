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
        return bookRepository.findBooksByTitle(title);
    }

    List<BookDTO> findByDate(String startDate, String endDate){
        return bookRepository.findBooksByDate(startDate, endDate);
    }

    BookDTO save(BookDTO bookDTO) {
        return bookRepository.create(bookDTO);
    }

    void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    BookDTO borrow(Long readerId, String readerName, String readerSurname, String bookTitle) {
        Reader borrower = null;
        BookDTO bookDTO = null;
        if (readerId != null) {
            borrower = readerRepository.findById(readerId);
        } else {
            System.err.println("You must provide id.");
        }
        if (borrower != null) {
            if (borrower.getName().equals(readerName) && borrower.getSurname().equals(readerSurname)) {
                bookDTO = bookRepository.findBooksByTitle(bookTitle).stream().filter(BookDTO::isAvailable).findAny().orElseThrow();
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
        return bookRepository.findAll();
    }

    String getAuthorByTitle(String title) {
        return bookRepository.getAuthorByTitle(title);
    }
}
