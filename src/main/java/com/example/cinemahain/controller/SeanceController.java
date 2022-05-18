package com.example.cinemahain.controller;

import com.example.cinemahain.models.Seance;
import com.example.cinemahain.repository.SeanceRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//Контроллер страницы сеансов
@Controller
public class SeanceController {

    private final SeanceRepo seanceRepo;
    // Констурктор с репозиториями сеансов
    public SeanceController(SeanceRepo seanceRepo) {
        this.seanceRepo = seanceRepo;
    }
    //Страница сеансов
    @GetMapping("/cinemas/{name}")
    public String seance(@PathVariable(value = "name") String name, Model model) {
        Iterable<Seance> seances = seanceRepo.findByCinemas_Name(name);
        model.addAttribute("seances", seances);
        return "seance";
    }
}
