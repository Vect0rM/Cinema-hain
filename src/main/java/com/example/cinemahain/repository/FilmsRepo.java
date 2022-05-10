package com.example.cinemahain.repository;

import com.example.cinemahain.models.Films;
import org.springframework.data.repository.CrudRepository;

public interface FilmsRepo extends CrudRepository<Films, Long> {
}
