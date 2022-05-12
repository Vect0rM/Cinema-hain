package com.example.cinemahain.controller;

import com.example.cinemahain.models.Cinemas;
import com.example.cinemahain.repository.CinemasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CinemasController {
    @Autowired
    private CinemasRepo cinemasRepo;

    @GetMapping("/cinemas")
    public String cinemas(Model model) {
        Iterable<Cinemas> cinemas = cinemasRepo.findAll();
        model.addAttribute("cinemas", cinemas);
        return "cinemas";
    }
}
