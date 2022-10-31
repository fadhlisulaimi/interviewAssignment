package com.assignment.DXCApplication.controller;

import com.assignment.DXCApplication.entity.Book;
import com.assignment.DXCApplication.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookStoreController {

    @Autowired
    private BookStoreService bookStoreService;


    @PostMapping(value = "/add")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        return new ResponseEntity<Book>(bookStoreService.addBook(newBook), HttpStatus.CREATED);
    }


    @PutMapping(value = "/update/{isbn}")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<Book> updateBook(@PathVariable("isbn") String isbn, @RequestBody Book bookToUpdate) {
        return new ResponseEntity<Book>(bookStoreService.updateBook(isbn, bookToUpdate), HttpStatus.OK);
    }

    @PutMapping(value = "/return")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<Book> returnBook(@RequestBody Book bookToReturn) {
        return new ResponseEntity<Book>(bookStoreService.returnBook(bookToReturn), HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{isbn}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable("isbn") String isbn) {
        bookStoreService.deleteBook(isbn);
        return new ResponseEntity<String>("Book deleted successfully", HttpStatus.OK);
    }


}
