package com.epam.library.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {

    private final BookService bookService;

    private final BookCookie bookCookie;

    @Autowired
    public BookController(BookService bookService, BookCookie bookCookie) {
        this.bookService = bookService;
        this.bookCookie = bookCookie;
    }

    @GetMapping(value = "", params = "bookId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> findById(@RequestParam("bookId") Long bookId) {
        BookDTO byId = bookService.findById(bookId);
        if (byId.getId() == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header(SET_COOKIE, bookCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, bookCookie.responseCookie.toString())
                .body(byId);
    }

    @GetMapping(value = "", params = "title", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDTO>> findByTitle(@RequestParam("title") String title) {

        List<BookDTO> byTitle = bookService.findByTitle(title);
        if (byTitle.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header(SET_COOKIE, bookCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, bookCookie.responseCookie.toString())
                .body(byTitle);
    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> findAllBooks(Model model) {
        List<BookDTO> all = bookService.findAll();
        if (all.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header(SET_COOKIE, bookCookie.responseCookie.toString())
                    .build();
        }
        model.addAttribute("books", all);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, bookCookie.responseCookie.toString())
                .body(all);
    }

    @GetMapping(value = "", params = {"startDate", "endDate"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDTO>> findByDate(@RequestParam("startDate") String startDate,
                                                    @RequestParam("endDate") String endDate) {
        List<BookDTO> byDate = bookService.findByDate(startDate, endDate);
        if (byDate.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header(SET_COOKIE, bookCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, bookCookie.responseCookie.toString())
                .body(byDate);
    }

    @PostMapping("")
    public ResponseEntity<BookDTO> save(@RequestBody @Validated BookDTO bookDTO) {
        BookDTO book = bookService.save(bookDTO);
        if (book == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .header(SET_COOKIE, bookCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(SET_COOKIE, bookCookie.responseCookie.toString())
                .body(book);
    }

    @DeleteMapping(value = "", params = "bookId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(@RequestParam("bookId") Long bookId) {
        bookService.delete(bookId);
        if (bookService.findById(bookId) != null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .header(SET_COOKIE, bookCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header(SET_COOKIE, bookCookie.responseCookie.toString())
                .build();
    }

    @PutMapping(value = "")
    public ResponseEntity<BookDTO> borrowBook(@RequestParam long readerId,
                                              @RequestParam String readerName,
                                              @RequestParam String readerSurname,
                                              @RequestParam String title) {
        BookDTO book = bookService.borrow(readerId, readerName, readerSurname, title);
        if (book == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .header(SET_COOKIE, bookCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header(SET_COOKIE, bookCookie.responseCookie.toString())
                .body(book);
    }

}
