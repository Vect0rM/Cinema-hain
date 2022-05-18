package com.example.cinemahain.controller;

import com.example.cinemahain.models.Cinemas;
import com.example.cinemahain.repository.CinemasRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//Контроллер страницы с кинотеатрами
@Controller
public class CinemasController {
    private final CinemasRepo cinemasRepo;
    //Конструктор с репозиторием кинотеатров
    public CinemasController(CinemasRepo cinemasRepo) {
        this.cinemasRepo = cinemasRepo;
    }
    //Страница выдачи кинотеатров
    @GetMapping("/cinemas")
    public String cinemas(Model model) {
        Iterable<Cinemas> cinemas = cinemasRepo.findAll();
        model.addAttribute("cinemas", cinemas);
        return "cinemas";
    }
}
