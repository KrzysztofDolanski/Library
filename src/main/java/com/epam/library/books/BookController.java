package com.epam.library.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @CacheEvict(value = "author")
    @GetMapping(value = "", params="title", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAuthor(@RequestParam("title") String title){
        return bookService.getAuthorByTitle(title);
    }


    @GetMapping(value = "", params = "bookId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> findById(@RequestParam("bookId") Long bookId) {
        BookDTO byId = bookService.findById(bookId);
        if (byId.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }
//
//    @GetMapping(value = "", params = "title", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<BookDTO>> findByTitle(@RequestParam("title") String title) {
//
//        List<BookDTO> byTitle = bookService.findByTitle(title);
//        if (byTitle.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(byTitle, HttpStatus.OK);
//    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> findAllBooks(Model model) {

        List<BookDTO> all = bookService.findAll();
        if (all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("books", all);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(value = "", params = {"startDate", "endDate"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDTO>> findByDate(@RequestParam("startDate") String startDate,
                                                    @RequestParam("endDate") String endDate) {
        List<BookDTO> byDate = bookService.findByDate(startDate, endDate);
        if (byDate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(byDate, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BookDTO> save(@RequestParam String title,
                                        @RequestParam String author) {

        BookDTO book = bookService.save(title, author);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "", params = "bookId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(@RequestParam("bookId") Long bookId) {
        bookService.delete(bookId);
        if (bookService.findById(bookId) != null) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "")
    public ResponseEntity<BookDTO> borrowBook(@RequestParam long readerId,
                                              @RequestParam String readerName,
                                              @RequestParam String readerSurname,
                                              @RequestParam String title) {
        BookDTO book = bookService.borrow(readerId, readerName, readerSurname, title);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
    }
}
