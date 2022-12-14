package com.epam.library.books;

import com.epam.library.readers.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.http.HttpHeaders.LOCATION;
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

    @GetMapping(value = "", params = {"title", "author"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDTO>> findByTitleAndAuthor(@RequestParam("title") String title,
                                                              @RequestParam("author") String author) {
        List<BookDTO> byTitle = bookService.findByTitleAndAuthor(title, author);
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

    public ResponseEntity<List<BookDTO>> findAllBooks() {
        List<BookDTO> all = bookService.findAll();
        if (all.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header(SET_COOKIE, bookCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(SET_COOKIE, bookCookie.responseCookie.toString())
                .body(all);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showAllBooks() {
        ResponseEntity<List<BookDTO>> allBooks = findAllBooks();
        ModelAndView mav = new ModelAndView("allBooks");
        mav.addObject("books", allBooks.getBody());
        return mav;
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


    @PostMapping(value = "",
            params = {"title", "readerId", "readerName", "readerSurname"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, "application/x-www-form-urlencoded"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> borrowBook(@RequestParam("title") String title,
                                              @RequestParam("readerId") Long readerId,
                                              @RequestParam("readerName") String readerName,
                                              @RequestParam("readerSurname") String readerSurname) {
        Reader reader = new Reader();
        reader.setId(readerId);
        reader.setName(readerName);
        reader.setSurname(readerSurname);

        BookDTO book = bookService.borrow(reader, title);
        if (book == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .header(SET_COOKIE, bookCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .header(SET_COOKIE, bookCookie.responseCookie.toString())
                .header(LOCATION, "/delivery")
                .body(book);
    }


    @PutMapping(value = "", params = {"bookId"})
    public ResponseEntity<BookDTO> giveBackBook(@RequestBody Reader reader,
                                                @RequestParam("bookId") long bookId) {
        BookDTO book = bookService.giveBackBook(reader, bookId);
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
