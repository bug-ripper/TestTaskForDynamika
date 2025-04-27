package com.denisbondd111.testtaskfordynamika.controller;

import com.denisbondd111.testtaskfordynamika.service.BookService;
import com.denisbondd111.testtaskfordynamika.service.ClientService;
import com.denisbondd111.testtaskfordynamika.service.RentalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rent")
public class RentalController {
    private RentalService rentalService;
    private ClientService clientService;
    private BookService bookService;

    public RentalController(RentalService rentalService, ClientService clientService, BookService bookService) {
        this.rentalService = rentalService;
        this.clientService = clientService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String rentBookForm(Model model) {
        model.addAttribute("clients", clientService.findAll(PageRequest.of(0, 100)));
        model.addAttribute("books", bookService.findAll(PageRequest.of(0, 100)));
        return "rental_form";
    }

    @PostMapping()
    public String rentBook(@RequestParam Long clientId, @RequestParam Long bookId) {
        rentalService.rentBook(clientId, bookId);
        return "redirect:/clients";
    }
}
