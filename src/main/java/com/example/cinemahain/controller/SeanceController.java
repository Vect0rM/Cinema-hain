package com.example.cinemahain.controller;

import com.example.cinemahain.models.*;
import com.example.cinemahain.repository.FilmsRepo;
import com.example.cinemahain.repository.GenresRepo;
import com.example.cinemahain.repository.SeanceRepo;
import com.example.cinemahain.repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.Set;

//Контроллер страницы сеансов
@Controller
public class SeanceController {

    private final SeanceRepo seanceRepo;
    private final UserRepo userRepo;
    private final FilmsRepo filmsRepo;
    private final GenresRepo genresRepo;
    // Констурктор с репозиториями сеансов
    public SeanceController(SeanceRepo seanceRepo, UserRepo userRepo, FilmsRepo filmsRepo, GenresRepo genresRepo) {
        this.seanceRepo = seanceRepo;
        this.userRepo = userRepo;
        this.filmsRepo = filmsRepo;
        this.genresRepo = genresRepo;
    }
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    String mainGenre(String st){
        String[] arr = st.split(" ");
        Arrays.sort(arr);
        String genre = "", word = "";
        int maxCount = 0, count = 1;

        for (String s : arr) {
            if (s.equals(word)) {
                count++;
            } else {
                if (count > maxCount) {
                    maxCount = count;
                    genre = word;
                }
                word = s;
                count = 1;
            }
        }

        if (count >= maxCount) {
            genre = word;
        }
        return genre;
    }
    //рекомендации
    Films recommendation(Set<Ticket> ticket){
        String topGenre = "";
        for (Ticket t:
             ticket) {
            topGenre = topGenre + t.getSeance().getFilms().getGenres().getName() + " ";
        }
        Genres genres = genresRepo.findByName(mainGenre(topGenre));
        Set<Films> films = genres.getFilms();
        return films.stream().findFirst().orElseThrow();
    }
    //Страница сеансов
    @GetMapping("/cinemas/{name}")
    public String seance(@PathVariable(value = "name") String name, Model model) {
        Iterable<Seance> seances = seanceRepo.findByCinemas_Name(name);
        User user = userRepo.findByUsername(getCurrentUsername());
        if (user != null ) {
            if (user.getTicket() != null && !user.getTicket().isEmpty()) {
                if (!recommendation(user.getTicket()).getName().isBlank()) {
                    model.addAttribute("rec", recommendation(user.getTicket()));
                }
            }
        }
        model.addAttribute("seances", seances);
        return "seance";
    }
}
