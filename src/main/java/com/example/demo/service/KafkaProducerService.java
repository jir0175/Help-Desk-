package com.example.demo.service;

import com.example.demo.dto.TicketCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    // Поменяй <Object, Object> на <String, Object>
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendTicketCreatedEvent(TicketCreatedEvent event) {
        kafkaTemplate.send("ticket-topic", event);
    }
}
