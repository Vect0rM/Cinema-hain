package com.example.cinemahain.repository;

import com.example.cinemahain.models.Films;
import com.example.cinemahain.models.Seance;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

//Репозиторий фильмов унаследованный от CrudRepository
public interface FilmsRepo extends CrudRepository<Films, Long> {
    Set<Films> findByGenresId(Long id);
    Films findByName(String name);
    Set<Films>findFilmsByGenresId(Long id);
}
