package com.denisbondd111.testtaskfordynamika.service;

import com.denisbondd111.testtaskfordynamika.repository.BookRepository;
import com.denisbondd111.testtaskfordynamika.entity.Book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void findAll_ReturnsPagedBooks() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Spring in Action");
        Page<Book> page = new PageImpl<>(Arrays.asList(book));
        when(bookRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Book> result = bookService.findAll(PageRequest.of(0, 10));

        assertEquals(1, result.getContent().size());
        assertEquals("Spring in Action", result.getContent().get(0).getTitle());
    }

    @Test
    void findById_ExistingId_ReturnsBook() {
        Book book = new Book();
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.findById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void findById_NonExistingId_ThrowsException() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> bookService.findById(1L));
    }

    @Test
    void save_SavesBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.save(book);

        assertEquals("Test Book", result.getTitle());
        verify(bookRepository).save(book);
    }
}