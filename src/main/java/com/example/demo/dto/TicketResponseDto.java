package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String authorUsername;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
