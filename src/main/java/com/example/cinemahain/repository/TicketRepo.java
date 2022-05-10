package com.example.cinemahain.repository;

import com.example.cinemahain.models.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepo extends CrudRepository<Ticket, Long> {
}
