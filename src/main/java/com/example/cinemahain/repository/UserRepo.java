package com.example.cinemahain.repository;

import com.example.cinemahain.models.User;
import org.springframework.data.repository.CrudRepository;
//Репозиторий пользователей унаследованный от CrudRepository
public interface UserRepo extends CrudRepository<User, Long> {
    //Поиск пользователя по имени
    User findByUsername(String username);
    //Проверка существования пользователя
    Boolean existsByUsername(String username);

}
