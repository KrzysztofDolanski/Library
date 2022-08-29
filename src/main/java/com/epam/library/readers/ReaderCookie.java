package com.epam.library.readers;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class ReaderCookie {

    ResponseCookie responseCookie = ResponseCookie.from("user-id", String.valueOf(ReaderId.createReaderCookieId()))
            .httpOnly(true)
            .path("/")
            .maxAge(8000)
            .build();
}
