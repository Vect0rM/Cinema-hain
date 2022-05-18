package com.example.cinemahain.repository;

import com.example.cinemahain.models.Workers;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
//Репозиторий сотрудников унаследованный от CrudRepository
public interface WorkersRepo extends CrudRepository<Workers, Long> {
    //Поиск сотрудника по id кинотеатра
    Set<Workers> findWorkersByCinemasId(Long id);
}
