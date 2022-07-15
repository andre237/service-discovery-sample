package com.andre.labs;

import com.andre.labs.books.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
public class Entrypoint implements GreetingResource {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(Entrypoint.class, args);
    }

    public String greeting() {
        return String.format("Hello from '%s'!", appName);
    }

    @GetMapping("/books")
    public List<BookEntity> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/books/filters")
    public List<BookEntity> filterBooks(BookFilterDTO filterDTO) {
        return bookService.filterBooks(filterDTO);
    }

    @GetMapping("/books/{isbn}")
    public BookEntity getBooks(@PathVariable String isbn) {
        return bookService.getBook(isbn);
    }

    @PatchMapping("/books/pageCount/{isbn}")
    public BookEntity updatePageCount(@PathVariable String isbn) {
        return bookService.updatePageCount(isbn);
    }

    @PostMapping("/books/random")
    public BookEntity generateRandomBook() {
        return bookService.generateRandomBook();
    }

}
