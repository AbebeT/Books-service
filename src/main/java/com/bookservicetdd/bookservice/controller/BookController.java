package com.bookservicetdd.bookservice.controller;

import com.bookservicetdd.bookservice.model.Book;
import com.bookservicetdd.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    ResponseEntity<Book> addBookRecord(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBookRecord(book), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<Iterable<Book>> findAllBooks() {
        return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Book> findBookById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteBookById(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<Book> updateBookById(@RequestBody Book book) throws Exception {
        return new ResponseEntity<>(bookService.updateBookById(book), HttpStatus.OK);
    }
}
