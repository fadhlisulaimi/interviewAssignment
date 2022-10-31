package com.assignment.DXCApplication.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "title")
    private String title;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_authors",
            joinColumns = { @JoinColumn(name = "book_id", referencedColumnName = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id", referencedColumnName = "author_id") }
    )
    private List<Author> authors = new ArrayList<>();


    @Column(name = "year")
    private int year;

    @Column(name = "price")
    private Double price;


    @Column(name = "genre")
    private String genre;

    @Column(name = "stock")
    private int stock;


    public Book() {

    }

    public Book(String isbn, String title, List<Author> authors, int year, Double price, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.price = price;
        this.genre = genre;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}