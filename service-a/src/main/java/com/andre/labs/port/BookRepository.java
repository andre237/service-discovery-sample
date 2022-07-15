package com.andre.labs.port;

import com.andre.labs.BookFilterDTO;
import com.andre.labs.books.BookEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;

@CacheConfig(cacheManager = "booksCacheManager", keyGenerator = "booksCacheKeyGen")
public interface BookRepository {

    @Cacheable(value = "allBooks")
    List<BookEntity> findAllBooks();

    @Cacheable(value = "filterBooks")
    List<BookEntity> findFilteredBooks(BookFilterDTO filter);

    @Cacheable(value = "books", key = "#isbn")
    BookEntity findBook(String isbn);

    @Caching(evict = {
            @CacheEvict(value = "allBooks", allEntries = true),
            @CacheEvict(value = "filterBooks", allEntries = true),
            @CacheEvict(value = "books", key = "#book.isbn")
    })
    void saveBook(BookEntity book);

}
