package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreateDto {
    @NotBlank(message = "cant be null")
    @Size(min = 3,max = 100, message = " be between 3 and 100 characters long.")
    private String title;
    @NotBlank(message = "cant be null")
    @Size(min = 3,max = 2000, message = " be between 3 and 2000 characters long.")
    private String description;
    @NotBlank(message = "cant be null")
    private String priority;
}
