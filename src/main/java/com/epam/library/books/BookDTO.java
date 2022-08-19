package com.epam.library.books;

import com.epam.library.readers.Reader;

public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private boolean available;
    private Reader reader;

    public BookDTO() {
    }

    public BookDTO(Long id, String title, String author, boolean available, Reader reader) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
        this.reader = reader;
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

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                ", reader=" + reader +
                '}';
    }
}
