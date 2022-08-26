package com.epam.library.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "", params = "bookId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> findById(@RequestParam("bookId") Long bookId) {
        BookDTO byId = bookService.findById(bookId);
        if (byId.getId()==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @RequestMapping(value = "", params = "title", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> findByTitle(@RequestParam("title") String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping("")
    public List<BookDTO> findAllBooks() {
        return bookService.findAll();
    }

    @RequestMapping(value = "", params = {"startDate", "endDate"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> findByDate(@RequestParam("startDate") String startDate,
                                    @RequestParam("endDate") String endDate) {
        return bookService.findByDate(startDate, endDate);
    }

    @PostMapping("")
    public BookDTO save(@RequestParam String title,
                        @RequestParam String author) {
        return bookService.save(title, author);
    }

    @DeleteMapping(value = "", params = "bookId", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@RequestParam("bookId") Long bookId) {
        bookService.delete(bookId);
    }

    @PutMapping("")
    public BookDTO borrowBook(@RequestParam long readerId,
                              @RequestParam String readerName,
                              @RequestParam String readerSurname,
                              @RequestParam String title) {
        return bookService.borrow(readerId, readerName, readerSurname, title);
    }

}
