package com.example.demo.controller;

import com.example.demo.dto.TicketCreateDto;
import com.example.demo.dto.TicketResponseDto;
import com.example.demo.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TicketResponseDto createTicket(@Valid @RequestBody TicketCreateDto ticket, @RequestParam UUID id){

        return ticketService.createTicket(ticket,id);
    };

    @GetMapping("/{id}")
    public TicketResponseDto getTicket(@PathVariable UUID id){
        return ticketService.getTicketById(id);
    }
    @PatchMapping("/{id}/status")
    public TicketResponseDto changeTicketStatus(@PathVariable UUID id,@RequestParam String status){
        return ticketService.updateStatus(id,status);
    }
    @GetMapping
    public List<TicketResponseDto> getAllTickets(){
        return ticketService.getAllTickets();
    }
}
