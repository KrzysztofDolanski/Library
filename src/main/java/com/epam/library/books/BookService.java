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
        BookDTO bookDTO = new BookDTO();
        try {
            Book book = bookRepository.findById(bookId).orElseThrow();
            bookDTO.setId(book.getId());
            bookDTO.setTitle(book.getTitle());
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setAvailable(book.isAvailable());
            bookDTO.setReader(book.getReader());
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return bookDTO;
    }

    public String save(String title, String author) {
        Book book = new Book.Builder()
                .title(title)
                .author(author)
                .available(true)
                .build();
        return bookRepository.save(book).toString();
    }

    public void delete(Long bookId) {
       bookRepository.deleteById(bookId);
    }

    @Override
    public String borrow(Long readerId, String readerName, String readerSurname, String bookTitle) {

        Reader borrower;
        Book book;

        if (readerId!=null){
        borrower = readerRepository.findById(readerId);
        } else {
            return "You must provide id.";
        }
        if (borrower.getName().equals(readerName)&& borrower.getSurname().equals(readerSurname)){
            book = bookRepository.findBookByTitle(bookTitle);
            if (book.isAvailable()){
                Long bookId = book.getId();
                bookRepository.update(false, borrower, bookId);
            }
        } else {
            return "Readers values mismatch.";
        }

       return "Book " + book.getTitle() + " successful rent";
    }
}
