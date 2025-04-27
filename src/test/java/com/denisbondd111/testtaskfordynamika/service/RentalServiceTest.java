package com.denisbondd111.testtaskfordynamika.service;

import com.denisbondd111.testtaskfordynamika.converter.RentalToReportConverter;
import com.denisbondd111.testtaskfordynamika.dto.RentalReportDTO;
import com.denisbondd111.testtaskfordynamika.entity.Book;
import com.denisbondd111.testtaskfordynamika.entity.Client;
import com.denisbondd111.testtaskfordynamika.entity.Rental;
import com.denisbondd111.testtaskfordynamika.repository.BookRepository;
import com.denisbondd111.testtaskfordynamika.repository.ClientRepository;
import com.denisbondd111.testtaskfordynamika.repository.RentalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {
    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private RentalToReportConverter converter;

    @InjectMocks
    private RentalService rentalService;

    @Test
    void rentBook_ValidIds_CreatesRental() {
        Client client = new Client();
        client.setId(1L);
        Book book = new Book();
        book.setId(1L);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(rentalRepository.save(any(Rental.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Rental result = rentalService.rentBook(1L, 1L);

        assertNotNull(result.getRentalDate());
        assertEquals(client, result.getClient());
        assertEquals(book, result.getBook());
        verify(rentalRepository).save(any(Rental.class));
    }

    @Test
    void rentBook_NonExistingClient_ThrowsException() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> rentalService.rentBook(1L, 1L));
    }

    @Test
    void getRentalReports_ReturnsReports() {
        Rental rental = new Rental();
        Client client = new Client();
        client.setFullName("John Doe");
        client.setBirthDate(LocalDate.of(1990, 1, 1));
        Book book = new Book();
        book.setTitle("Test Book");
        rental.setClient(client);
        rental.setBook(book);
        rental.setRentalDate(LocalDateTime.now());

        RentalReportDTO report = new RentalReportDTO();
        report.setClientFullName("John Smith");

        when(rentalRepository.findAll()).thenReturn(Arrays.asList(rental));
        when(converter.convert(rental)).thenReturn(report);

        List<RentalReportDTO> result = rentalService.getRentalReports();

        assertEquals(1, result.size());
        assertEquals("John Smith", result.get(0).getClientFullName());
        verify(converter).convert(rental);
    }
}