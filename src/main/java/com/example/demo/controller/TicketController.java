package com.example.demo.controller;

import com.example.demo.dto.TicketCreateDto;
import com.example.demo.dto.TicketResponseDto;
import com.example.demo.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/create")
    public TicketResponseDto createTicket(@Valid @RequestBody TicketCreateDto ticket, @RequestParam UUID id){
        return ticketService.createTicket(ticket,id);
    };

    @GetMapping("/{id}")
    public TicketResponseDto getTicket(@Valid @PathVariable UUID id){
        return ticketService.getTicketById(id);
    }
}
