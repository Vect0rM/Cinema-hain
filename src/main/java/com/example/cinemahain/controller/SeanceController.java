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
        StringBuilder topGenre = new StringBuilder();
        for (Ticket t:
             ticket) {
            topGenre.append(t.getSeance().getFilms().getGenres().getName()).append(" ");
        }
        Genres genres = genresRepo.findByName(mainGenre(topGenre.toString()));
        Set<Films> films = genres.getFilms();
        StringBuilder genresFilms = new StringBuilder();
        for (Films f :
                films) {
            genresFilms.append(f.getName()).append("&&");
        }
        for (Ticket t:
             ticket) {
            if (t.getSeance().getFilms().getGenres().getName().equals(genres.getName())) {
                genresFilms = new StringBuilder(genresFilms.toString().replace(t.getSeance().getFilms().getName(), ""));
            }
        }
        Films film;
        String[] films1 = genresFilms.toString().split("&&");
        if (films1.length != 0) {
            for (String s :
                    films1) {
                if (!s.isEmpty()) {
                    film = filmsRepo.findByName(s);
                    return film;
                }
            }
        } else {
            return new Films("ErroR");
        }
        return new Films("ErroR");
    }
    //Страница сеансов
    @GetMapping("/cinemas/{name}")
    public String seance(@PathVariable(value = "name") String name, Model model) {
        Iterable<Seance> seances = seanceRepo.findByCinemas_Name(name);
        User user = userRepo.findByUsername(getCurrentUsername());
        if (user != null ) {
            if (user.getTicket() != null && !user.getTicket().isEmpty()) {
                if (!recommendation(user.getTicket()).getName().equals("ErroR")) {
                    Films films = recommendation(user.getTicket());
                    model.addAttribute("rec", films);
                } else {
                    model.addAttribute("rec", "ErroR");
                }
            } else {
                model.addAttribute("rec", "ErroR");
            }
        }
        model.addAttribute("seances", seances);
        return "seance";
    }
}
