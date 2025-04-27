package com.denisbondd111.testtaskfordynamika.service;

import com.denisbondd111.testtaskfordynamika.converter.RentalToReportConverter;
import com.denisbondd111.testtaskfordynamika.dto.RentalReportDTO;
import com.denisbondd111.testtaskfordynamika.entity.Book;
import com.denisbondd111.testtaskfordynamika.entity.Client;
import com.denisbondd111.testtaskfordynamika.entity.Rental;
import com.denisbondd111.testtaskfordynamika.repository.BookRepository;
import com.denisbondd111.testtaskfordynamika.repository.ClientRepository;
import com.denisbondd111.testtaskfordynamika.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    private RentalToReportConverter converter;

    private RentalRepository rentalRepository;
    private BookRepository bookRepository;
    private ClientRepository clientRepository;

    public RentalService(RentalToReportConverter converter, RentalRepository rentalRepository, BookRepository bookRepository, ClientRepository clientRepository) {
        this.converter = converter;
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    public Rental rentBook(Long clientId, Long bookId) {
        Client client = clientRepository.findById(clientId).orElseThrow(IllegalArgumentException::new);
        Book book = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
        Rental rental = new Rental();
        rental.setClient(client);
        rental.setBook(book);
        rental.setRentalDate(LocalDateTime.now());
        return rentalRepository.save(rental);
    }

    public List<RentalReportDTO> getRentalReports() {
        return rentalRepository.findAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
