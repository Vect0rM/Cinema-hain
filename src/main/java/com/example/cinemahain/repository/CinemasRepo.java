package com.example.cinemahain.repository;

import com.example.cinemahain.models.Cinemas;
import org.springframework.data.repository.CrudRepository;
//Репозиторий кинотеатров унаследованный от CrudRepository
public interface CinemasRepo extends CrudRepository<Cinemas, Long> {
}
