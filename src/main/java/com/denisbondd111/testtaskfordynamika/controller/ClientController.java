package com.denisbondd111.testtaskfordynamika.controller;

import com.denisbondd111.testtaskfordynamika.entity.Client;
import com.denisbondd111.testtaskfordynamika.service.ClientService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public String listClients(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("clients", clientService.findAll(PageRequest.of(page, 10)));
        return "clients";
    }

    @GetMapping("/new")
    public String newClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients_form";
    }

    @PostMapping()
    public String saveClient(@Valid @ModelAttribute Client client, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "clients_form";
        }
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String editClientForm(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.findById(id));
        return "clients_form";
    }
}
