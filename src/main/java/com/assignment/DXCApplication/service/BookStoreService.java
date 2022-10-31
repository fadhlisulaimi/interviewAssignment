package com.assignment.DXCApplication.service;

import com.assignment.DXCApplication.entity.Author;
import com.assignment.DXCApplication.entity.Book;
import com.assignment.DXCApplication.entity.SearchQuery;
import com.assignment.DXCApplication.exception.ResourceNotFoundException;
import com.assignment.DXCApplication.repository.AuthorRepository;
import com.assignment.DXCApplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookStoreService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    //check against authors and book name
    private Book checkIfBookExists(Book book){
        Book tmpBook = null;

        List<Book> existingBooks = bookRepository.findByTitle(book.getTitle());

        //sort book authors name
        List<String> newBookAuthorNames = book.getAuthors()
                .stream()
                .map(author -> author.getName())
                .collect(Collectors.toList())
                .stream()
                .sorted()
                .collect(Collectors.toList());

        for(Book b: existingBooks){
            List<String> authorNames = b.getAuthors()
                    .stream()
                    .map(author -> author.getName())
                    .collect(Collectors.toList())
                    .stream()
                    .sorted()
                    .collect(Collectors.toList());

            if(authorNames.equals(newBookAuthorNames)){
                tmpBook = b;
                break;
            }
        }

        return tmpBook;
    }

    public Book addBook(Book newBook){

        UUID uuid = UUID.randomUUID();

        Book book = checkIfBookExists(newBook);

        //if book already exists, update stock
        if(book != null){
            book.setStock(book.getStock() + 1);
            return bookRepository.save(book);
        }

        //else add new book
        newBook.setIsbn(uuid.toString());
        newBook.setStock(1);
        return bookRepository.save(newBook);
    }


    public Book updateBook(String isbn, Book bookToUpdate){

        if(isbn == null || isbn.isEmpty()){
            throw new ResourceNotFoundException("Book", "Title", bookToUpdate.getTitle());
        }

        Book existingBook = bookRepository.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book", "Title", isbn));

        existingBook.setTitle(bookToUpdate.getTitle());

        List<String> existingAuthorsName = existingBook.getAuthors().stream().map(author -> author.getName()).collect(Collectors.toList());
        List<Author> tmpAuthors = new ArrayList<>();

        bookToUpdate.getAuthors().stream().forEach(newAuthor -> {
            if(existingAuthorsName.contains(newAuthor.getName())){
                Author existingAuthor = existingBook.getAuthors().stream().filter(author -> author.getName().equals(newAuthor.getName())).findFirst().orElse(null);
                if(existingAuthor != null){
                    tmpAuthors.add(existingAuthor);
                }
            }else{
                tmpAuthors.add(newAuthor);
            }
        });


        existingBook.setAuthors(tmpAuthors);
        existingBook.setTitle(bookToUpdate.getTitle());
        existingBook.setGenre(bookToUpdate.getGenre());
        existingBook.setYear(bookToUpdate.getYear());
        existingBook.setPrice(bookToUpdate.getPrice());
        return bookRepository.save(existingBook);
    }


    public Book searchByBook(SearchQuery searchQuery){

        Book tmpBook = new Book();
        tmpBook.setTitle(searchQuery.getTitle());
        tmpBook.setAuthors(searchQuery.getAuthors().stream().map(authorName -> new Author(authorName, null)).collect(Collectors.toList()));

        Book existingBook = checkIfBookExists(tmpBook);

        if(existingBook == null){
            throw new ResourceNotFoundException("Book", "Title", tmpBook.getTitle());
        }

        return existingBook;
    }

    public void deleteBook(String isbn){
        Book existingBook = bookRepository.findByIsbn(isbn).orElseThrow(() -> new ResourceNotFoundException("Book", "Title", isbn));
        bookRepository.delete(existingBook);
    }



//    public Book updateBook(Book bookToUpdate){
//
//        String oldTitle = bookToUpdate.getOldTitle();
//
//        if(oldTitle == null || oldTitle.isEmpty()){
//            throw new ResourceNotFoundException("Book", "Title", bookToUpdate.getOldTitle());
//        }
//
//        Book existingBook = checkIfBookExists(bookToUpdate, bookToUpdate.getOldTitle());
//        if(existingBook == null){
//            throw new ResourceNotFoundException("Book", "Title", bookToUpdate.getOldTitle());
//        }
//
//        existingBook.setTitle(bookToUpdate.getTitle());
//
//        List<String> existingAuthorsName = existingBook.getAuthors().stream().map(author -> author.getName()).collect(Collectors.toList());
//        List<Author> tmpAuthors = new ArrayList<>();
//
//        bookToUpdate.getAuthors().stream().forEach(newAuthor -> {
//            if(existingAuthorsName.contains(newAuthor.getName())){
//                Author existingAuthor = existingBook.getAuthors().stream().filter(author -> author.getName().equals(newAuthor.getName())).findFirst().orElse(null);
//                if(existingAuthor != null){
//                    tmpAuthors.add(existingAuthor);
//                }
//            }else{
//                tmpAuthors.add(newAuthor);
//            }
//        });
//
//
//        existingBook.setAuthors(tmpAuthors);
//        existingBook.setTitle(bookToUpdate.getTitle());
//        existingBook.setGenre(bookToUpdate.getGenre());
//        existingBook.setYear(bookToUpdate.getYear());
//        existingBook.setPrice(bookToUpdate.getPrice());
//        return bookRepository.save(existingBook);
//    }
}
