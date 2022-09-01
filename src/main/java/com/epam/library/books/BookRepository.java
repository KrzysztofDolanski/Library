package com.epam.library.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final BookDAOCreator bookDAOCreator;

    @Autowired
    public BookRepository(BookDAOCreator bookDAOCreator) {
        this.bookDAOCreator = bookDAOCreator;
    }


    BookDTO findById(long id) {
        return BookMapper.mapToDTO(bookDAOCreator.getReaderDAO().findById(id));
    }

    BookDTO create(String title, String author) {
        Book book = new Book.Builder().title(title).author(author).build();
        return BookMapper.mapToDTO(bookDAOCreator.getReaderDAO().create(book));
    }

    void deleteById(long id) {
        bookDAOCreator.getReaderDAO().deleteById(id);
    }


    BookDTO update(BookDTO bookDTO) {
        return BookMapper.mapToDTO(bookDAOCreator.getReaderDAO().update(BookMapper.mapToBook(bookDTO)));
    }

    List<BookDTO> findBooksByTitle(String bookTitle) {
        return bookDAOCreator.getReaderDAO().findByTitle(bookTitle).stream().map(BookMapper::mapToDTO).toList();
    }


    List<BookDTO> findBooksByDate(String startDate, String endDate) {
        return bookDAOCreator.getReaderDAO().findByDate(startDate, endDate).stream().map(BookMapper::mapToDTO).toList();
    }

    void deleteByTitleAuthorAvailable(String title, String author, boolean available) {
        bookDAOCreator.getReaderDAO().deleteByTitleAuthorAvailable(title, author, available);
    }

    void deleteByTitle(String title) {
        bookDAOCreator.getReaderDAO().deleteByTitle(title);
    }

    void deleteAllBooks() {
        bookDAOCreator.getReaderDAO().deleteAllBooks();
    }

    List<BookDTO> findAll() {
        return bookDAOCreator.getReaderDAO().findAll().stream().map(BookMapper::mapToDTO).toList();
    }

    public String getAuthorByTitle(String title) {
        return bookDAOCreator.getReaderDAO().getAuthorsByTitle(title).get(0);
    }
}
