package com.epam.library.readers;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class ReaderDeleteCookie {

    ResponseCookie readerDeleteCookie = ResponseCookie
            .from("user-id", "deleted_reader")
            .httpOnly(true)
            .path("/")
            .maxAge(8000)
            .build();

}
