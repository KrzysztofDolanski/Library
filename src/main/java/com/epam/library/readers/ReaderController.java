package com.epam.library.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/readers")
public class ReaderController {


    private final ReaderService readerService;
    private final ReaderCookie readerCookie;

    @Autowired
    public ReaderController(ReaderService readerService, ReaderCookie readerCookie) {
        this.readerService = readerService;
        this.readerCookie = readerCookie;
    }

    @PostMapping("")
    public ResponseEntity<Reader> create(@RequestBody Reader reader) {
        Reader createdReader = readerService.create(reader);
        if (createdReader==null){
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .header(HttpHeaders.SET_COOKIE, readerCookie.responseCookie.toString())
                    .build();
        }
        return ResponseEntity.ok(createdReader);
    }


    @GetMapping(value = "", params = "readerId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reader> findById(@RequestParam("readerId") long readerId) {

        Reader reader = readerService.findById(readerId);
        if (reader==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reader, HttpStatus.OK);
    }

    @GetMapping(value = "", params = {"readerName, ReaderSurname"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reader>> findByNameAndSurname(@RequestParam("readerName") String readerName,
                                             @RequestParam("readerSurname") String readerSurname) {
        List<Reader> byNameAndSurname = readerService.findByNameAndSurname(readerName, readerSurname);
        if (byNameAndSurname.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(byNameAndSurname, HttpStatus.OK);
    }

    @DeleteMapping(value = "", params = "readerId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@RequestParam("readerId") long readerId) {
        readerService.deleteById(readerId);
        if (readerService.findById(readerId)!=null){
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Reader> update(@RequestBody Reader reader) {
        Reader updated = readerService.update(reader);
        if (updated==null){
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
    }

}
