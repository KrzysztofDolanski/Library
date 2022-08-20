package com.epam.library.books;

import com.epam.library.atheneum.BorrowBook;
import com.epam.library.readers.Reader;
import com.epam.library.readers.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements BorrowBook {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    @Autowired
    public BookService(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public BookDTO findById(Long bookId) {
        BookDTO bookDTO = null;
        try {
            bookDTO = bookRepository.findById(bookId);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return bookDTO;
    }

    public BookDTO save(String title, String author) {
        return bookRepository.create(title, author);
    }

    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public String borrow(Long readerId, String readerName, String readerSurname, String bookTitle) {
        Reader borrower;
        BookDTO bookDTO;
        if (readerId != null) {
            borrower = readerRepository.findById(readerId);
        } else {
            return "You must provide id.";
        }
        if (borrower.getName().equals(readerName) && borrower.getSurname().equals(readerSurname)) {
            bookDTO = bookRepository.findBookByTitle(bookTitle).stream().filter(book -> book.isAvailable() == true).findAny().orElseThrow();
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
}
