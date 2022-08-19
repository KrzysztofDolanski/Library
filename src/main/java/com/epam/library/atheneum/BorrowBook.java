package com.epam.library.atheneum;

import com.epam.library.books.Book;
import com.epam.library.readers.Reader;

public interface BorrowBook {

    String borrow(Long readerId, String readerName, String readerSurname, String bookTitle);
}
