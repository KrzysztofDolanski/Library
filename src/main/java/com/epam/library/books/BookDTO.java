package com.epam.library.books;

import com.epam.library.cache.DTO;
import com.epam.library.readers.Reader;

import java.util.Date;

public class BookDTO extends DTO {

    private Long id;
    private String title;
    private String author;
    private boolean available;
    private Reader reader;

    private Date date;

    private Long times_of_borrowing;

    public BookDTO() {
    }

    public BookDTO(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTimes_of_borrowing() {
        return times_of_borrowing;
    }

    public void setTimes_of_borrowing(Long times_of_borrowing) {
        this.times_of_borrowing = times_of_borrowing;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                ", reader=" + reader +
                ", date=" + date +
                ", times_of_borrowing=" + times_of_borrowing +
                '}';
    }
}
