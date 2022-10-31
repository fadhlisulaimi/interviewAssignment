package com.assignment.DXCApplication.repository;

import com.assignment.DXCApplication.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select book from Book book where book.isbn = :#{#isbn}")
    Optional<Book> findByIsbn(@Param("isbn") String isbn);

    @Query("select book from Book book where book.title = :#{#title}")
    List<Book> findByTitle(@Param("title") String title);
}
