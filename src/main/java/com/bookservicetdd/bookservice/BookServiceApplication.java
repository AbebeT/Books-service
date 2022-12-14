package com.bookservicetdd.bookservice;

import com.bookservicetdd.bookservice.model.Book;
import com.bookservicetdd.bookservice.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(BookRepository bookRepository) {
		return args -> {
			List<Book> books = Arrays.asList(Book
							.builder()
							.title("Oromay")
							.summary("Amharic fictional book by Bealu Girma")
							.rating(4)
					.imgSrc("https://template.canva.com/EAFCv6WKX6M/2/0/251w-q8_Gjl2bU_Y.jpg")
					.timeCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
							.build(),
					Book.builder()
							.title("Home Sapiens")
							.imgSrc("https://template.canva.com/EAFFDo_Dt7I/1/0/251w-vRvp6U_Jftc.jpg")
							.summary("A Brief History of Humankind")
							.rating(5)
							.timeCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
							.build()

			);
			books.forEach(book -> {
				bookRepository.save(book);
			});
			bookRepository.findAll().forEach(System.out::println);
		};
	}

}
