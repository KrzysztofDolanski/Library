package com.epam.library.readers;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class ReaderIdCookie {

    private Reader reader = new Reader();

    ResponseCookie readerIdCookie = ResponseCookie
            .from("user-id", String.valueOf(ReaderId.createReaderCookieId(reader)))
            .httpOnly(true)
            .path("/")
            .maxAge(8000)
            .build();

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
