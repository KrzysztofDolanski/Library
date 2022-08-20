package com.epam.library.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/readers")
public class ReaderController {


    private final ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping("")
    public Reader create(@RequestParam String name,
                         @RequestParam String surname,
                         @RequestParam String email) {
        return readerService.create(name, surname, email);
    }


    @GetMapping("{id}")
    public String findById(@PathVariable long id) {
        return readerService.findById(id);
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        readerService.deleteById(id);
    }
}
