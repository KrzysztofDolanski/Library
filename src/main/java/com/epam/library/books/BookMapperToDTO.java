package com.epam.library.books;

public class BookMapperToDTO {
    public static BookDTO mapToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setAvailable(book.isAvailable());
        bookDTO.setReader(book.getReader());
        bookDTO.setDate(book.getRent_date());
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
                .build();
    }
}
