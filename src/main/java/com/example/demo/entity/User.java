package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", updatable = false, nullable = false)
    private UUID id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn (name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name="username",nullable = false,unique = true)
    private String username;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="password_hash",nullable = false)
    private String passwordHash;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @PrePersist
    protected void toInsert(){
        this.createdAt =LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    };

    @PreUpdate
    protected void toUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

}
