package com.epam.library.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RestController
@RequestMapping("/readers")
@CrossOrigin("*")
public class ReaderController {


    private final ReaderService readerService;
    private final ReaderCookie readerCookie;

    @Autowired
    public ReaderController(ReaderService readerService, ReaderCookie readerCookie) {
        this.readerService = readerService;
        this.readerCookie = readerCookie;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reader> create(@RequestBody @Validated Reader reader) {
        Reader createdReader = readerService.create(reader);
        if (createdReader == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .header(SET_COOKIE, readerCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, readerCookie.responseCookie.toString())
                .body(createdReader);
    }

    @GetMapping(value = "", params = "readerId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reader> findById(@RequestParam("readerId") long readerId) {

        Reader reader = readerService.findById(readerId);
        if (reader == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header(SET_COOKIE, readerCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, readerCookie.responseCookie.toString())
                .body(reader);
    }

    @GetMapping(value = "", params = {"readerName, ReaderSurname"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reader>> findByNameAndSurname(@RequestParam("readerName") String readerName,
                                                             @RequestParam("readerSurname") String readerSurname) {
        List<Reader> byNameAndSurname = readerService.findByNameAndSurname(readerName, readerSurname);
        if (byNameAndSurname.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header(SET_COOKIE, readerCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, readerCookie.responseCookie.toString())
                .body(byNameAndSurname);
    }

    @DeleteMapping(value = "", params = "readerId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@RequestParam("readerId") long readerId) {
        readerService.deleteById(readerId);
        if (readerService.findById(readerId) != null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .header(SET_COOKIE, readerCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, readerCookie.responseCookie.toString())
                .build();
    }

    @PutMapping("")
    public ResponseEntity<Reader> update(@RequestBody Reader reader) {
        Reader updated = readerService.update(reader);
        if (updated == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .header(SET_COOKIE, readerCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header(SET_COOKIE, readerCookie.responseCookie.toString())
                .body(updated);
    }

}
