package com.example.demo.repository;

import com.example.demo.entity.Ticket;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findByAuthor(User author);
    List<Ticket> findByStatus(String status);
}
