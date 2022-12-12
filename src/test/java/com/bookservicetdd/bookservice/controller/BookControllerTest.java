package com.bookservicetdd.bookservice.controller;

import com.bookservicetdd.bookservice.model.Book;
import com.bookservicetdd.bookservice.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    @MockBean
    BookService bookService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("should get all the record books")
    void getAllBooksTest() throws Exception {
        List<Book> books = new ArrayList<>(Arrays.asList(
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

        when(bookService.findAllBooks()).thenReturn(books);

        mockMvc.perform(
                get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2)))
        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("should find book by Id")
    void findBookByIdTest() throws Exception {
        Book book = Book.builder()
                .id(1L)
                .title("1984")
                .summary("Fictional book by George Orwell")
                .rating(5)
                .build();

        when(bookService.findBookById(1L)).thenReturn(book);

        mockMvc.perform(get("/api/books/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("1984"))
                .andExpect(status().isOk());
    }

//    @DisplayName("should delete boo by Id")
//    @Test
//    void deleteBookById() throws Exception {
//        Book book = Book.builder()
//                .id(1L)
//                .title("1984")
//                .summary("Fictional book by George Orwell")
//                .rating(5)
//                .build();
//
//        when(bookService.deleteBookById(1L)).then();
//
//        mockMvc.perform(delete("/api/books/{id}", 1L)
//        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
////                .andExpect(jsonPath("$.title").value("1984"));
//
//    }

    @DisplayName("should update a book")
    @Test
    void updateBookById() throws Exception {
        Book book = Book.builder()
                .id(1L)
                .title("1984")
                .summary("Fictional book by George Orwell")
                .rating(5)
                .build();

        when(bookService.updateBookById(book)).thenReturn(book);

        mockMvc.perform(patch("/api/books/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("1984"))
                .andExpect(status().isOk());
    }
}
