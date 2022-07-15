package com.andre.labs;

import com.andre.labs.books.BookEntity;
import com.andre.labs.port.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getBooks() {
        return bookRepository.findAllBooks();
    }

    public List<BookEntity> filterBooks(BookFilterDTO filter) {
        return bookRepository.findFilteredBooks(filter);
    }

    public BookEntity getBook(String isbn) {
        return bookRepository.findBook(isbn);
    }

    public BookEntity updatePageCount(String isbn) {
        BookEntity book = bookRepository.findBook(isbn);
        book.setPageCount(book.getPageCount() + 1);
        bookRepository.saveBook(book);
        return book;
    }

    public BookEntity generateRandomBook() {
        BookEntity book = new BookEntity("jj4129", "Test random", 674, "Author random");
        bookRepository.saveBook(book);
        return book;
    }

}
