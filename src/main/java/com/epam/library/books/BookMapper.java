package com.epam.library.books;

public class BookMapper {
    public static BookDTO mapToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setAvailable(book.isAvailable());
        bookDTO.setReader(book.getReader());
        bookDTO.setDate(book.getRent_date());
        bookDTO.setTimes_of_borrowing(book.getTimes_of_borrowing());
        return bookDTO;
    }

    public static Book mapToBook(BookDTO bookDTO) {
        return new Book.Builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .available(bookDTO.isAvailable())
                .reader(bookDTO.getReader())
                .date(bookDTO.getDate())
                .timesOfBorrowing(bookDTO.getTimes_of_borrowing())
                .build();
    }
}
