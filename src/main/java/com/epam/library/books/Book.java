package com.epam.library.books;

import com.epam.library.database.DataTransferObject;
import com.epam.library.readers.Reader;

import java.util.Date;

public class Book implements DataTransferObject {

    private Long id;
    private String title;
    private String author;
    private boolean available;
    private Reader reader;
    private Date rent_date;

    private Long times_of_borrowing;
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public Reader getReader() {
        return reader;
    }

    public Date getRent_date() {
        return rent_date;
    }

    public void setRent_date(Date rent_date) {
        this.rent_date = rent_date;
    }

    public Long getTimes_of_borrowing() {
        return times_of_borrowing;
    }

    public void setTimes_of_borrowing(Long times_of_borrowing) {
        this.times_of_borrowing = times_of_borrowing;
    }

    public static final class Builder {
        Long id;
        String title;
        String author;
        boolean available;
        Reader reader;
        Date date;
        Long times_of_borrowing;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder available(boolean available) {
            this.available = available;
            return this;
        }

        public Builder reader(Reader reader) {
            this.reader = reader;
            return this;
        }

        public Builder date(Date date){
            this.date = date;
            return this;
        }

        public Builder timesOfBorrowing(Long timesOfBorrowing){
            this.times_of_borrowing = timesOfBorrowing;
            return this;
        }

        public Book build() {
            if (title.isEmpty()) {
                throw new IllegalStateException("Cannot save book without title");
            }
            Book book = new Book();
            book.id = this.id;
            book.title = this.title;
            book.author = this.author;
            book.available = this.available;
            book.reader = this.reader;
            book.rent_date = this.date;
            book.times_of_borrowing = this.times_of_borrowing;
            return book;
        }
    }
}