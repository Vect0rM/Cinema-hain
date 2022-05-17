package com.example.cinemahain.repository;

import com.example.cinemahain.models.Halls;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface HallsRepo extends CrudRepository<Halls, Long> {
    Set<Halls>findHallsByCinemasId(Long id);
}
