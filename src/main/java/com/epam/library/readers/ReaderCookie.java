package com.epam.library.readers;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class ReaderCookie {

    ResponseCookie responseCookie = ResponseCookie.from("user-id", String.valueOf(ReaderId.createReaderCookieId()))
            .httpOnly(true)
            .path("/readers")
            .maxAge(8000)
            .domain("localhost:8082")
            .build();
}
