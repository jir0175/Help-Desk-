package com.example.demo.service;

import com.example.demo.dto.TicketCreateDto;
import com.example.demo.dto.TicketResponseDto;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    TicketResponseDto createTicket(TicketCreateDto createDto, UUID author);
    TicketResponseDto getTicketById(UUID id);
    TicketResponseDto updateStatus(UUID id, String status);
    List<TicketResponseDto> getAllTickets();
}
