package com.epam.library.books;

import com.epam.library.database.DataTransferObject;
import com.epam.library.readers.Reader;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BOOKS")
public class Book implements DataTransferObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private boolean available;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Reader reader;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static final class Builder {
        Long id;
        String title;
        String author;
        boolean available;
        Reader reader;
        Date date;

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
            book.date = this.date;
            return book;
        }
    }
}