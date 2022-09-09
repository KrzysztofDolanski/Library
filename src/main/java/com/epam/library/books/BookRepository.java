package com.epam.library.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    private final BookDAOCreator bookDAOCreator;

    @Autowired
    public BookRepository(BookDAOCreator bookDAOCreator) {
        this.bookDAOCreator = bookDAOCreator;
    }


    Optional<BookDTO> findById(long id) {
        return Optional.of(BookMapper.mapToDTO(bookDAOCreator.getReaderDAO().findById(id)));
    }

    Optional<BookDTO> create(BookDTO bookDTO) {
        Book book = new Book.Builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .available(bookDTO.isAvailable())
                .reader(bookDTO.getReader())
                .build();
        return Optional.of(BookMapper.mapToDTO(bookDAOCreator.getReaderDAO().create(book)));
    }

    void deleteById(long id) {
        bookDAOCreator.getReaderDAO().deleteById(id);
    }


    Optional<BookDTO> update(BookDTO bookDTO) {
        return Optional.of(BookMapper.mapToDTO(bookDAOCreator.getReaderDAO().update(BookMapper.mapToBook(bookDTO))));
    }

    List<Optional<BookDTO>> findBooksByTitle(String bookTitle) {
        return bookDAOCreator.getReaderDAO().findByTitle(bookTitle).stream().map(BookMapper::mapToDTO).map(Optional::of).toList();
    }

    List<Optional<BookDTO>> findBooksByDate(String startDate, String endDate) {
        return bookDAOCreator.getReaderDAO().findByDate(startDate, endDate).stream().map(BookMapper::mapToDTO).map(Optional::of).toList();
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

    List<Optional<BookDTO>> findAll() {
        return bookDAOCreator.getReaderDAO().findAll().stream().map(BookMapper::mapToDTO).map(Optional::of).toList();
    }

    String getAuthorByTitle(String title) {
        return bookDAOCreator.getReaderDAO().getAuthorsByTitle(title).get(0);
    }

    List<Optional<BookDTO>> findBooksByTitleAndAuthor(String title, String author) {
        return bookDAOCreator.getReaderDAO().findByTitleAndAuthor(title, author).stream().map(BookMapper::mapToDTO).map(Optional::of).toList();
    }
}
