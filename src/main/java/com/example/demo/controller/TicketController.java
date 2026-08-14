package com.example.demo.controller;

import com.example.demo.dto.TicketCreateDto;
import com.example.demo.dto.TicketResponseDto;
import com.example.demo.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/create/{id}")
    public TicketResponseDto createTicket(@RequestBody TicketCreateDto ticket, @PathVariable UUID id){
        return ticketService.createTicket(ticket,id);
    };

    @GetMapping("/{id}")
    public TicketResponseDto getTicket(@PathVariable UUID id){
        return ticketService.getTicketById(id);
    }
}
