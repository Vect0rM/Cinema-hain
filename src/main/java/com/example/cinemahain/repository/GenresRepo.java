package com.example.cinemahain.repository;

import com.example.cinemahain.models.Genres;
import org.springframework.data.repository.CrudRepository;

public interface GenresRepo extends CrudRepository<Genres, Long> {
    Genres findByName(String name);
}
