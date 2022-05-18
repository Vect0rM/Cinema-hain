package com.example.cinemahain.controller;

import com.example.cinemahain.models.Films;
import com.example.cinemahain.models.Seance;
import com.example.cinemahain.models.Ticket;
import com.example.cinemahain.models.User;
import com.example.cinemahain.repository.FilmsRepo;
import com.example.cinemahain.repository.SeanceRepo;
import com.example.cinemahain.repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

//Контроллер страницы сеансов
@Controller
public class SeanceController {

    private final SeanceRepo seanceRepo;
    private final UserRepo userRepo;
    private final FilmsRepo filmsRepo;
    // Констурктор с репозиториями сеансов
    public SeanceController(SeanceRepo seanceRepo, UserRepo userRepo, FilmsRepo filmsRepo) {
        this.seanceRepo = seanceRepo;
        this.userRepo = userRepo;
        this.filmsRepo = filmsRepo;
    }
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    //рекомендации
    String recommendation(Set<Ticket> ticket){
        Iterable<Films> films = filmsRepo.findAll();
        String s = "";
        for (Films f:
                films) {
            s = s + f.getName() + " ";
        }
        for (Ticket t:
                ticket) {
            s = s.replace(t.getSeance().getFilm(), "%%");
        }
        String[] rec = s.split("%% ");
        return "Фильм который вам может понравится: " + rec[1];
    }
    //Страница сеансов
    @GetMapping("/cinemas/{name}")
    public String seance(@PathVariable(value = "name") String name, Model model) {
        Iterable<Seance> seances = seanceRepo.findByCinemas_Name(name);
        User user = userRepo.findByUsername(getCurrentUsername());
        if (user != null ) {
            if (user.getTicket() != null && !user.getTicket().isEmpty()) {
                if (!recommendation(user.getTicket()).isBlank()) {
                    model.addAttribute("rec", recommendation(user.getTicket()));
                }
            }
        }
        model.addAttribute("seances", seances);
        return "seance";
    }
}
