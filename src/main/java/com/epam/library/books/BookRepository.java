package com.epam.library.books;

import com.epam.library.readers.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("Select b FROM Book b WHERE b.title = :title")
    Book findBookByTitle(@Param("title") String title);

    @Modifying
    @Query("UPDATE Book b set b.available = :available, b.reader = :reader where b.id = :bookId")
    void update(@Param("available") boolean available,
                @Param("reader") Reader reader,
                @Param("bookId") Long id);
}
