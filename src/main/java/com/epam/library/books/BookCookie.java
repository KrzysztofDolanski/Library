package com.epam.library.books;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class BookCookie {

    ResponseCookie responseCookie = ResponseCookie.from("user-id", String.valueOf(BookId.createBookCookieId()))
            .httpOnly(true)
            .path("/")
            .maxAge(8000)
            .build();
}
