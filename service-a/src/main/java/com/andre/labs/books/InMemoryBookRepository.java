package com.andre.labs.books;

import com.andre.labs.BookFilterDTO;
import com.andre.labs.port.BookRepository;

import lombok.SneakyThrows;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private final Map<String, BookEntity> bookMap = new HashMap<>();

    public InMemoryBookRepository() {
        bookMap.put("492jf2", new BookEntity("492jf2", "Test 1", 809, "Author 1"));
        bookMap.put("k67233", new BookEntity("k67233", "Test 2", 777, "Author 2"));
        bookMap.put("nn4823", new BookEntity("nn4823", "Test 3", 560, "Author 3"));
        bookMap.put("645gb1", new BookEntity("645gb1", "Test 4", 145, "Author 4"));
    }

    @Override
    public List<BookEntity> findAllBooks() {
        this.delayInSecs(2);
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public List<BookEntity> findFilteredBooks(BookFilterDTO filter) {
        this.delayInSecs(2);
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public BookEntity findBook(String isbn) {
        this.delayInSecs(2);
        return Optional.ofNullable(bookMap.get(isbn)).orElseThrow(NoSuchElementException::new);
    }

    public void saveBook(BookEntity book) {
        bookMap.remove(book.getIsbn());
        bookMap.put(book.getIsbn(), book);
    }

    @SneakyThrows
    private void delayInSecs(Integer seconds) {
        TimeUnit.SECONDS.sleep(seconds);
    }

}
