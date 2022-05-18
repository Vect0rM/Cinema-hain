package com.example.cinemahain.repository;

import com.example.cinemahain.models.Role;
import org.springframework.data.repository.CrudRepository;
//Репозиторий ролей унаследованный от CrudRepository
public interface RoleRepo extends CrudRepository<Role, Long> {
}
