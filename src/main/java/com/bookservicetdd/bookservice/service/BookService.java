package com.bookservicetdd.bookservice.service;

import com.bookservicetdd.bookservice.model.Book;
import com.bookservicetdd.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    ;

    public Book findBookById(long id) throws Exception {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new Exception();
        }
        return book.get();
    }

    public void deleteBookById(long id) throws Exception {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new Exception();
        }
        bookRepository.deleteById(id);
    }

    public Book updateBookById(Book book) throws Exception {
        Optional<Book> bk = bookRepository.findById(book.getId());
        if (!bk.isPresent()) {
            throw new Exception();
        }
        return bookRepository.save(book);

    }

    public Book addBookRecord(Book book) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = now.format(format);
        book.setTimeCreated(formatDateTime);
        return bookRepository.save(book);
    }
}
