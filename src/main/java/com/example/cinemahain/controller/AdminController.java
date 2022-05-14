package com.example.cinemahain.controller;

import com.example.cinemahain.models.Films;
import com.example.cinemahain.models.Promotions;
import com.example.cinemahain.repository.FilmsRepo;
import com.example.cinemahain.repository.PromotionsRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminController {

    final private FilmsRepo filmsRepo;
    final private PromotionsRepo promotionsRepo;

    public AdminController(FilmsRepo filmsRepo, PromotionsRepo promotionsRepo) {
        this.filmsRepo = filmsRepo;
        this.promotionsRepo = promotionsRepo;
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        return "admin";
    }
    @GetMapping("/admin/films")
    public String adminPanelFilms(Model model) {
        Iterable<Films> films = filmsRepo.findAll();
        model.addAttribute("films", films);
        return "adminPanel/adminFilms";
    }
    @PostMapping("/admin/films")
    public String adminPanelFilmsPost(@RequestParam String id, @RequestParam String name, @RequestParam String photoSrc, @RequestParam String text, Model model) {
        Films film;
        if (id.isEmpty()) {
            film = new Films(name, text, photoSrc);
        } else {
            film = filmsRepo.findById(Long.valueOf(id)).get();
            if (!name.isEmpty()) {
                film.setName(name);
            }
            if (!text.isEmpty()) {
                film.setText(text);
            }
            if (!photoSrc.isEmpty()) {
                film.setPhotoSrc(photoSrc);
            }
        }
        filmsRepo.save(film);
        return "redirect:/admin";
    }
    @GetMapping("/admin/promotions")
    public String adminPanelPromotions(Model model) {
        Iterable<Promotions> promotions = promotionsRepo.findAll();
        model.addAttribute("promotions", promotions);
        return "adminPanel/adminPromotions";
    }
    @PostMapping("/admin/promotions")
    public String adminPanelPromotionsPost(@RequestParam String id, @RequestParam String name, @RequestParam String text, Model model) {
        Promotions promotions;
        if (id.isEmpty()) {
            promotions = new Promotions(name, text);
        } else {
            promotions = promotionsRepo.findById(Long.valueOf(id)).get();
            if (!name.isEmpty()) {
                promotions.setName(name);
            }
            if (!text.isEmpty()) {
                promotions.setText(text);
            }
        }
        promotionsRepo.save(promotions);
        return "redirect:/admin";
    }
}
