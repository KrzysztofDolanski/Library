package com.epam.library.readers;

import com.epam.library.readers.exceptions.ReaderNotFoundException;
import com.epam.library.readers.exceptions.ReaderUpdateException;
import com.epam.library.readers.exceptions.ReadersBooksNotReturnedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.*;

@RestController
@RequestMapping("/readers")
@CrossOrigin("*")
public class ReaderController {

    private final ReaderService readerService;
    private final ReaderIdCookie readerIdCookie;
    private final ReaderDeleteCookie readerDeleteCookie;

    @Autowired
    public ReaderController(ReaderService readerService, ReaderIdCookie readerIdCookie, ReaderDeleteCookie readerDeleteCookie) {
        this.readerService = readerService;
        this.readerIdCookie = readerIdCookie;
        this.readerDeleteCookie = readerDeleteCookie;
    }

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, "application/x-www-form-urlencoded"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reader> create(@ModelAttribute @Validated Reader reader, Model model) {
        Reader createdReader = new Reader();
        String cookie = "";
        try {
            createdReader = readerService.create(reader);
            readerIdCookie.setReader(reader);
            cookie += readerIdCookie.readerIdCookie.toString();
        } catch (ReaderNotFoundException e) {
            System.err.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .header(SET_COOKIE, cookie)
                    .build();
        }
        model.addAttribute("reader", createdReader);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, cookie)
                .header(CONTENT_TYPE, "application/json")
                .body(createdReader);
    }

    @GetMapping(value = "", params = "readerId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reader> findById(@RequestParam("readerId") long readerId) {
        Reader reader;
        String cookie = "";
        try {
            reader = readerService.findById(readerId).orElseThrow(ReaderNotFoundException::new);
            readerIdCookie.setReader(reader);
            cookie += readerIdCookie.readerIdCookie.toString();
        } catch (ReaderNotFoundException e) {
            System.err.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header(SET_COOKIE, cookie)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, cookie)
                .body(reader);
    }

    @GetMapping(value = "", params = {"readerName", "readerSurname"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reader>> findByNameAndSurname(@RequestParam("readerName") String readerName,
                                                             @RequestParam("readerSurname") String readerSurname) {
        List<Reader> readers;

        String cookie = "";
        //todo: Here returning is list of Readers but cookie is produces from first Reader from the list.
        try {
            readers = new ArrayList<>(readerService.findByNameAndSurname(readerName, readerSurname));
            readerIdCookie.setReader(readers.get(0));
            cookie += readerIdCookie.readerIdCookie.toString();
        } catch (ReaderNotFoundException e) {
            System.err.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header(SET_COOKIE, cookie)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, cookie)
                .body(readers);
    }

    @DeleteMapping(value = "", params = "readerId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@RequestParam("readerId") long readerId) {
        String cookie = "";
        try {
            readerIdCookie.setReader(readerService.findById(readerId).orElseThrow(ReaderNotFoundException::new));
            List<String> bookTitles = readerService.findReadersBooks(readerId);
            if (bookTitles.isEmpty()) {
                readerService.deleteById(readerId);
                cookie += readerIdCookie.readerIdCookie.toString();
            } else {
                throw new ReadersBooksNotReturnedException();
            }
        } catch (ReaderNotFoundException | ReadersBooksNotReturnedException e) {
            System.err.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .header(SET_COOKIE, cookie)
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, cookie)
                .header(SET_COOKIE2, readerDeleteCookie.readerDeleteCookie.toString())
                .build();
    }

    @PutMapping("")
    public ResponseEntity<Reader> update(@RequestBody Reader reader) {
        String cookie = "";
        Reader updated;
        try {
            updated = readerService.update(reader).orElseThrow(ReaderUpdateException::new);
            readerIdCookie.setReader(reader);
            cookie += readerIdCookie.readerIdCookie.toString();
        } catch (ReaderNotFoundException | ReaderUpdateException e) {
            System.err.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .header(SET_COOKIE, cookie)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header(SET_COOKIE, cookie)
                .body(updated);
    }
}
