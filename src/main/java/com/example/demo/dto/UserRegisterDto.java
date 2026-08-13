package com.example.demo.dto;

import jakarta.validation.constraints.Email;
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


public class UserRegisterDto {
    @NotBlank(message = "Cant be null")
    @Size(max = 50,min = 3, message = "The name must contain between 3 and 50 characters.")
    private String username;
    @NotBlank(message = "Cant be null")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Cant be null")
    @Size(max = 50,min = 3, message = "The name must contain between 3 and 50 characters.")
    private String firstName;
    @NotBlank(message = "Cant be null")
    @Size(max = 50,min = 3, message = "The name must contain between 3 and 50 characters.")
    private String lastName;
    @NotBlank(message = "Cant be null")
    @Size(min = 6, max = 20, message = "The password must be between 6 and 20 characters long.")
    private String password;

}
