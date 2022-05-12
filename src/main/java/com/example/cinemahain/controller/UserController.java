package com.example.cinemahain.controller;

import com.example.cinemahain.models.User;
import com.example.cinemahain.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

  @GetMapping("/settings/{username}")
    public String User(@PathVariable(value = "username") String username, Model model) {
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);
        return "user";
    }
    @PostMapping("/settings/{username}")
    public String UserEdit(@PathVariable(value = "username") String username, Model model, @RequestParam String surname, @RequestParam String name, @RequestParam String email,@RequestParam long num,@RequestParam String username1, @RequestParam String pass, @RequestParam String passConf) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepo.findByUsername(username);
        user.setSurname(surname);
        user.setName(name);
        user.setEmail(email);
        user.setNum(num);
        user.setUsername(username1);
        if (!pass.isEmpty()) {
            user.setPassword(passwordEncoder.encode(pass));
            user.setPasswordConfirm(passwordEncoder.encode(passConf));
        }
        userRepo.save(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }
}
