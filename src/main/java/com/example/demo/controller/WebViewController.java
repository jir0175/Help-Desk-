package com.example.demo.controller;

import com.example.demo.dto.TicketCreateDto;
import com.example.demo.dto.TicketResponseDto;
import com.example.demo.dto.TicketCreatedEvent;
import com.example.demo.service.KafkaProducerService;
import com.example.demo.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class WebViewController {

    private final TicketService ticketService;
    private final KafkaProducerService kafkaProducerService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "index";
    }

    @PostMapping("/web/tickets")
    public String createTicket(@RequestParam String title,
                               @RequestParam String description,
                               @RequestParam String authorEmail,
                               @RequestParam(required = false) UUID authorId) {

        // 1. Собираем DTO для сервиса
        TicketCreateDto dto = new TicketCreateDto();
        dto.setTitle(title);
        dto.setDescription(description);

        // Если authorId не передан с формы, генерируем временно дефолтный UUID
        UUID userId = (authorId != null) ? authorId : UUID.randomUUID();

        // 2. Вызываем метод из TicketService
        TicketResponseDto createdTicket = ticketService.createTicket(dto, userId);

        // 3. Формируем событие для Kafka и отправляем
        TicketCreatedEvent event = new TicketCreatedEvent(
                createdTicket.getId(),
                createdTicket.getTitle(),
                authorEmail,
                createdTicket.getStatus().toString()
        );
        kafkaProducerService.sendTicketCreatedEvent(event);

        return "redirect:/";
    }
}
