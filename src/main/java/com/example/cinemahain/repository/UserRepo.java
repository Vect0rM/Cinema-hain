package com.example.cinemahain.repository;

import com.example.cinemahain.models.Ticket;
import com.example.cinemahain.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);

}
