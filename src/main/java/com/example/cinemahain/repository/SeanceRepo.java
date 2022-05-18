package com.example.cinemahain.repository;

import com.example.cinemahain.models.Seance;
import org.springframework.data.repository.CrudRepository;
//Репозиторий сеансов унаследованный от CrudRepository
public interface SeanceRepo extends CrudRepository<Seance, Long> {
    //Поиск сеанса по имени кинотеатра
    Iterable<Seance> findByCinemas_Name(String name);
}
