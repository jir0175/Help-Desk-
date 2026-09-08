package com.example.demo.service;

import com.example.demo.dto.TicketCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "ticket-topic", groupId = "helpdesk-group")
    public void consumeTicketCreated(TicketCreatedEvent event) {
        log.info("Успешно получено событие из Kafka по тикету: {}", event.getTicketId());
        log.info("Детали события: Название='{}', Email автора='{}', Статус='{}'",
                event.getTitle(), event.getAuthorEmail(), event.getStatus());
    }
}
