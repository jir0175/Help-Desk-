package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreatedEvent implements Serializable {
    private final static long serialVersionUID = 1L;
    private UUID ticketId;
    private String title;
    private String authorEmail;
    private String status;

}
