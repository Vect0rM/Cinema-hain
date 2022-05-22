package com.example.cinemahain.repository;

import com.example.cinemahain.models.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

//Репозиторий билетов унаследованный от CrudRepository
public interface TicketRepo extends CrudRepository<Ticket, Long> {
    //Поиск билетов по id сеанса
    Set<Ticket> findTicketBySeanceId(Long id);
    Ticket findTicketBySeanceIdAndPlace(Long id, Long place);
}
