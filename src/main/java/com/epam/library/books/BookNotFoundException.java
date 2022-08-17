package com.epam.library.books;

public class NoBookFindException extends Exception{

    @Override
    public String getMessage(String id) {
        return "Book with id " + id +" not found";
    }


}
