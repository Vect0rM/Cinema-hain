package com.example.cinemahain.controller;

import com.example.cinemahain.models.Role;
import com.example.cinemahain.models.User;
import com.example.cinemahain.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {
    
    private final UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@RequestParam String username, @RequestParam String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User(username , passwordEncoder.encode(password), passwordEncoder.encode(password), Collections.singleton(new Role(1L, "ROLE_USER")));
        userRepo.save(user);
        return "redirect:/";
    }

}
