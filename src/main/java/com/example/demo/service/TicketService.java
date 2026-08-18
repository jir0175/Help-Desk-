package com.example.demo.service;

import com.example.demo.dto.TicketCreateDto;
import com.example.demo.dto.TicketResponseDto;
import com.example.demo.entity.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    TicketResponseDto createTicket(TicketCreateDto createDto, java.util.UUID author);
    TicketResponseDto getTicketById(UUID id);
    TicketResponseDto updateStatus(UUID id,String status);
    List<TicketResponseDto> getAllTickets();
}
