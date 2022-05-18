package com.example.cinemahain.repository;

import com.example.cinemahain.models.Halls;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
//Репозиторий залов унаследованный от CrudRepository
public interface HallsRepo extends CrudRepository<Halls, Long> {
    //Поиск зала по id кинотеатра
    Set<Halls>findHallsByCinemasId(Long id);
}
