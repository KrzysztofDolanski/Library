package com.epam.library.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @GetMapping("{bookId}")
    public BookDTO findById(@PathVariable Long bookId) {
        return bookService.findById(bookId);
    }

    @GetMapping("")
    public List<BookDTO> findByTitle(@RequestParam String bookTitle) {
        return bookService.findByTitle(bookTitle);
    }


    @GetMapping("")
    public List<BookDTO> findByDate(@RequestParam String startDate,
                                    @RequestParam String endDate) {
        return bookService.findByDate(startDate, endDate);
    }

    @PostMapping("")
    public BookDTO save(@RequestParam String title,
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
