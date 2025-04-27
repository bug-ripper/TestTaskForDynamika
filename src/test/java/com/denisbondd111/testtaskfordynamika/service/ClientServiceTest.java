package com.denisbondd111.testtaskfordynamika.service;

import com.denisbondd111.testtaskfordynamika.entity.Client;
import com.denisbondd111.testtaskfordynamika.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void findAll_ReturnsPagedClients() {
        Client client = new Client();
        client.setId(1L);
        client.setFullName("John Doe");
        Page<Client> page = new PageImpl<>(Arrays.asList(client));
        when(clientRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Client> result = clientService.findAll(PageRequest.of(0, 10));

        assertEquals(1, result.getContent().size());
        assertEquals("John Doe", result.getContent().get(0).getFullName());
    }

    @Test
    void findById_ExistingId_ReturnsClient() {
        Client client = new Client();
        client.setId(1L);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client result = clientService.findById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void findById_NonExistingId_ThrowsException() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> clientService.findById(1L));
    }

    @Test
    void save_SavesClient() {
        Client client = new Client();
        client.setFullName("Test Client");
        client.setBirthDate(LocalDate.of(1990, 1, 1));
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client result = clientService.save(client);

        assertEquals("Test Client", result.getFullName());
        verify(clientRepository).save(client);
    }
}