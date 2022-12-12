package com.bookservicetdd.bookservice;


import com.bookservicetdd.bookservice.model.Book;
import com.bookservicetdd.bookservice.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Book book;

    @BeforeEach
    void setup(){
        book = Book.builder()
                .id(2L)
                .title("1984")
                .summary("Fictional book by George Orwell")
                .rating(4)
                .build();

        jdbcTemplate.execute("INSERT INTO Books(id, title, summary, rating)  values (1L, '1984', 'Fictional Book by Orwell', 4)");
    }

    @Test
    void findBookByIdTest() {
        Optional<Book> book1 = bookRepository.findById(1L);

        book1.ifPresent(value -> assertEquals(value.getTitle(), book.getTitle()));


    }




}
