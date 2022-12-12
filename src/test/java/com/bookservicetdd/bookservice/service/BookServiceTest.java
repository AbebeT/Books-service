package com.bookservicetdd.bookservice.service;

import com.bookservicetdd.bookservice.model.Book;
import com.bookservicetdd.bookservice.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    private List<Book> books;

    @BeforeEach
    void setUp() {
        System.out.println("Testing BookService has started.");
        books = new ArrayList<>(Arrays.asList(
                Book.builder()
                        .id(1L)
                        .title("1984")
                        .summary("Fictional book by George Orwell")
                        .rating(5)
                        .build(),
                Book.builder()
                        .id(2L)
                        .title("Animal farm")
                        .summary("Fictional book by George Orwell")
                        .rating(4)
                        .build()
        ));

    }

    @Test
    @DisplayName("should find all books")
    void findAllBooksTest(){
        when(bookRepository.findAll()).thenReturn(books);
        assertEquals(books.size(),bookService.findAllBooks().spliterator().getExactSizeIfKnown());
    }

    @Test
    @DisplayName("should find book by Id")
    void findBookByIdTest(){

        when(bookRepository.findAll()).thenReturn(books);
        assertEquals(books.size(),bookService.findAllBooks().spliterator().getExactSizeIfKnown());
    }


    @AfterEach
    void tearDown() {
        System.out.println("Testing BookService has ended.");
    }
}
