package com.example.library.controller;

import com.example.library.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class LibraryController {

    private List<Book> bookList = new ArrayList<>(Arrays.asList(
        new Book(1, "Clean Code", "Robert C. Martin", 599.00),
        new Book(2, "The Pragmatic Programmer", "Andrew Hunt", 749.00),
        new Book(3, "Effective Java", "Joshua Bloch", 699.00)
    ));

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Online Library System!";
    }

    @GetMapping("/count")
    public int getTotalBooks() {
        return bookList.size();
    }

    @GetMapping("/price")
    public double getSamplePrice() {
        return 499.99;
    }

    @GetMapping("/books")
    public List<String> getAllBookTitles() {
        List<String> titles = new ArrayList<>();
        for (Book book : bookList) {
            titles.add(book.getTitle());
        }
        return titles;
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable int id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return "ID: " + book.getId()
                    + ", Title: " + book.getTitle()
                    + ", Author: " + book.getAuthor()
                    + ", Price: " + book.getPrice();
            }
        }
        return "Book not found with ID: " + id;
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam String title) {
        for (Book book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return "Book found: " + book.getTitle() + " by " + book.getAuthor();
            }
        }
        return "No book found with title: " + title;
    }

    @GetMapping("/author/{name}")
    public String getByAuthor(@PathVariable String name) {
        List<String> result = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getAuthor().equalsIgnoreCase(name)) {
                result.add(book.getTitle());
            }
        }
        if (result.isEmpty()) {
            return "No books found for author: " + name;
        }
        return "Books by " + name + ": " + result;
    }

    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        book.setId(bookList.size() + 1);
        bookList.add(book);
        return "Book added successfully: " + book.getTitle();
    }

    @GetMapping("/viewbooks")
    public List<Book> viewAllBooks() {
        return bookList;
    }
}
