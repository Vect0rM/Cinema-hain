package com.example.cinemahain.repository;

import com.example.cinemahain.models.Ticket;
import com.example.cinemahain.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TicketRepo extends CrudRepository<Ticket, Long> {
    Set<Ticket> findTicketBySeanceId(Long id);

}
