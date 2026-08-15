package com.example.demo.service.impl;

import com.example.demo.dto.TicketCreateDto;
import com.example.demo.dto.TicketResponseDto;
import com.example.demo.entity.Ticket;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TicketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Transactional
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    @Override
    public TicketResponseDto createTicket(TicketCreateDto ticketCreateDto,java.util.UUID author){
        User userAuthor = userRepository.findById(author)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + author));
        Ticket ticket = new Ticket();
        ticket.setAuthor(userAuthor);
        ticket.setPriority(ticketCreateDto.getPriority());
        ticket.setDescription(ticketCreateDto.getDescription());
        ticket.setTitle(ticketCreateDto.getTitle());
        ticket.setStatus("NEW");
        Ticket savedTicket = ticketRepository.save(ticket);
        TicketResponseDto ticketResponse = new TicketResponseDto();
        ticketResponse.setStatus(savedTicket.getStatus());
        ticketResponse.setPriority(savedTicket.getPriority());
        ticketResponse.setDescription(savedTicket.getDescription());
        ticketResponse.setTitle(savedTicket.getTitle());
        ticketResponse.setAuthorUsername(userAuthor.getUsername());
        return ticketResponse;
    };
    @Override
    public TicketResponseDto getTicketById(UUID id){
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
        User authorUsername = ticket.getAuthor();
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setAuthorUsername(authorUsername.getUsername());
        ticketResponseDto.setDescription(ticket.getDescription());
        ticketResponseDto.setPriority(ticket.getPriority());
        ticketResponseDto.setStatus(ticket.getStatus());
        ticketResponseDto.setId(ticket.getId());
        ticketResponseDto.setTitle(ticket.getTitle());
        return ticketResponseDto;
    }


}
