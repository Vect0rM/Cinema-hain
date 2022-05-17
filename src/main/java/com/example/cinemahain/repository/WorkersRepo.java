package com.example.cinemahain.repository;

import com.example.cinemahain.models.Workers;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface WorkersRepo extends CrudRepository<Workers, Long> {
    Set<Workers> findWorkersByCinemasId(Long id);
}
