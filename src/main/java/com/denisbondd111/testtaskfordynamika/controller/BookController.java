package com.denisbondd111.testtaskfordynamika.controller;

import com.denisbondd111.testtaskfordynamika.entity.Book;
import com.denisbondd111.testtaskfordynamika.service.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping()
    public String listBooks(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("books", bookService.findAll(PageRequest.of(page, 10)));
        return "books";
    }

    @GetMapping("/new")
    public String newBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "books_form";
    }

    @PostMapping()
    public String saveBook(@Valid @ModelAttribute Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books_form";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "books_form";
    }
}
