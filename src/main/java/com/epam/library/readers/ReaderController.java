package com.epam.library.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {


    private final ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping("")
    public Reader create(@RequestBody Reader reader) {
        return readerService.create(reader);
    }


    @GetMapping(value = "", params = "readerId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reader findById(@RequestParam("readerId") long readerId) {
        return readerService.findById(readerId);
    }

    @GetMapping(value = "", params = {"readerName, ReaderSurname"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reader> findByNameAndSurname(@RequestParam("readerName") String readerName,
                                             @RequestParam("readerSurname") String readerSurname) {
        return readerService.findByNameAndSurname(readerName, readerSurname);
    }

    @DeleteMapping(value = "", params = "readerId", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestParam("readerId") long readerId) {
        readerService.deleteById(readerId);
    }

    @PutMapping("")
    public Reader update(@RequestBody Reader reader) {
        return readerService.update(reader);
    }

}
