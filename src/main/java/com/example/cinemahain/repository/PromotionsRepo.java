package com.example.cinemahain.repository;

import com.example.cinemahain.models.Promotions;
import org.springframework.data.repository.CrudRepository;
//Репозиторий акций унаследованный от CrudRepository
public interface PromotionsRepo extends CrudRepository<Promotions, Long> {
}
