package com.example.cinemahain.repository;

import com.example.cinemahain.models.Films;
import org.springframework.data.repository.CrudRepository;
//Репозиторий фильмов унаследованный от CrudRepository
public interface FilmsRepo extends CrudRepository<Films, Long> {
}
