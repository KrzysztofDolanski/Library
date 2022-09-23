package com.epam.library.books;

import com.epam.library.books.exceptions.BookNotBorrowedByGivenReaderException;
import com.epam.library.books.exceptions.BookNotFoundException;
import com.epam.library.books.exceptions.SaveBookException;
import com.epam.library.readers.Reader;
import com.epam.library.readers.ReaderService;
import com.epam.library.readers.exceptions.ReaderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
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

    @Cacheable(cacheNames = "booksById")
    public BookDTO findById(Long bookId) {
        BookDTO bookDTO = null;
        try {
            bookDTO = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return bookDTO;
    }

    @Cacheable(cacheNames = "booksTitle")
    public List<BookDTO> findByTitle(String title) {
        return bookRepository.findBooksByTitle(title)
                .stream()
                .map(Optional::get)
                .toList();
    }

    @Cacheable(cacheNames = "booksDate")
    public List<BookDTO> findByDate(String startDate, String endDate) {
        return bookRepository.findBooksByDate(startDate, endDate)
                .stream()
                .map(Optional::get)
                .toList();
    }

    BookDTO save(BookDTO bookDTO) {
        return bookRepository.create(bookDTO).orElseThrow(SaveBookException::new);
    }

    void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    BookDTO borrow(Reader reader, String bookTitle) {
        Reader borrower = null;
        BookDTO bookDTO = null;
        if (reader.getId() != null) {
            borrower = readerService.findById(reader.getId()).orElseThrow(ReaderNotFoundException::new);
        } else {
            System.err.println("You must provide id.");
        }
        if (borrower != null) {
            if (borrower.getName().toLowerCase().contains(reader.getName().toLowerCase())
                    && borrower.getSurname().toLowerCase().contains(reader.getSurname().toLowerCase())) {
                bookDTO = bookRepository.findBooksByTitle(bookTitle)
                        .stream()
                        .map(Optional::get)
                        .filter(BookDTO::isAvailable)
                        .findAny()
                        .orElseThrow();
                if (bookDTO.isAvailable()) {
                    bookDTO.setReader(borrower);
                    bookDTO.setAvailable(false);
                    bookDTO.setTimes_of_borrowing(bookDTO.getTimes_of_borrowing() + 1);
                    bookRepository.update(bookDTO);
                }
            } else {
                System.err.println("Readers values mismatch.");
            }
        }
        return bookDTO;
    }


    BookDTO giveBackBook(Reader reader, long bookId) {
        if (reader.getBooks()
                .stream()
                .filter(book -> book.getId() == bookId)
                .toList()
                .isEmpty())
            throw new BookNotBorrowedByGivenReaderException(bookId, reader.getId());
        BookDTO bookDTO = bookRepository.findById(bookId).get();
        bookDTO.setReader(new Reader());
        bookDTO.setAvailable(true);
        bookRepository.update(bookDTO);
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

    @Cacheable(cacheNames = "allBooks")
    public List<BookDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(Optional::get)
                .toList();
    }

    String getAuthorByTitle(String title) {
        return bookRepository.getAuthorByTitle(title);
    }

    @Cacheable(cacheNames = "booksByTitleAndAuthor")
    public List<BookDTO> findByTitleAndAuthor(String title, String author) {
        return bookRepository
                .findBooksByTitleAndAuthor(title, author)
                .stream()
                .map(Optional::get)
                .toList();
    }
}
