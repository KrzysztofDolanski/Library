package com.epam.library.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("{bookId}")
    public String findById(@PathVariable Long bookId) {
        return bookService.findById(bookId).toString();
    }

    @PostMapping("")
    public String save(@RequestParam String title,
                       @RequestParam String author) {
        return bookService.save(title, author);
    }

    @DeleteMapping("{bookId}")
    public void delete(@PathVariable Long bookId) {
        bookService.delete(bookId);
    }


    @PutMapping("")
    public String borrowBook(@RequestParam long readerId,
                             @RequestParam String readerName,
                             @RequestParam String readerSurname,
                             @RequestParam String title){
        return bookService.borrow(readerId, readerName, readerSurname, title);
    }
}
