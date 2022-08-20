package com.epam.library.atheneum;

public interface BorrowBook {

    String borrow(Long readerId, String readerName, String readerSurname, String bookTitle);
}
