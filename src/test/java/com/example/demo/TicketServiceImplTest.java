package com.example.demo;

import com.example.demo.dto.TicketResponseDto;
import com.example.demo.entity.Ticket;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceImplTest {
    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Test
    void getTicketById_Success(){
        UUID ticketId = UUID.randomUUID();
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setTitle("тестовый тайтл");
        ticket.setStatus("Тестовый статус");
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        TicketResponseDto result = ticketService.getTicketById(ticketId);
        assertNotNull(result);
        assertEquals(ticketId,result.getId());
        assertEquals("тестовый тайтл",result.getTitle());
        verify(ticketRepository,times(1)).findById(ticketId);
    }
    @Test
    void getTicketById_NotFound_ThrowsException(){
        UUID ticketId = UUID.randomUUID();
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> ticketService.getTicketById(ticketId));
        verify(ticketRepository,times(1)).findById(ticketId);
    }
}
