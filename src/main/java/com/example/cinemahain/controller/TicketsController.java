package com.example.cinemahain.controller;

import com.example.cinemahain.models.Films;
import com.example.cinemahain.models.Seance;
import com.example.cinemahain.models.Ticket;
import com.example.cinemahain.models.User;
import com.example.cinemahain.repository.SeanceRepo;
import com.example.cinemahain.repository.TicketRepo;
import com.example.cinemahain.repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

//Контроллер билетов и их покупки
@Controller
public class TicketsController {

    private final TicketRepo ticketRepo;
    private final UserRepo userRepo;
    private final SeanceRepo seanceRepo;
    //Конструктор с репозиториями билетов и пользователей
    public TicketsController(TicketRepo ticketRepo, UserRepo userRepo, SeanceRepo seanceRepo) {
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
        this.seanceRepo = seanceRepo;
    }
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    //Страница с выбором билета
    @GetMapping("/cinemas/{name}/{id}")
    public String tickets(@PathVariable(value = "name") String name, @PathVariable(value = "id") Long id, Model model) {
        Seance seance = seanceRepo.findById(id).get();
        Films films = seance.getFilms();
        Iterable<Ticket> tickets = ticketRepo.findTicketBySeanceId(id);
        model.addAttribute("film", films);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }
    @PostMapping("/cinemas/{name}/{id}")
    public String ticketsPost(@PathVariable(value = "name") String name, @PathVariable(value = "id") Long id,@RequestParam(defaultValue = "-1") Long[] ticket ,Model model) {
        User user = userRepo.findByUsername(getCurrentUsername());
        System.out.println(Arrays.toString(ticket));
        Seance seance = seanceRepo.findById(id).get();
        Films films = seance.getFilms();
        Iterable<Ticket> tickets = ticketRepo.findTicketBySeanceId(id);
        model.addAttribute("film", films);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }
    //Страница с покупкой билета
    @GetMapping("/cinemas/{name}/{id}/{Tid}")
    public String purchase(@PathVariable(value = "name") String name, @PathVariable(value = "id") Long id, @PathVariable(value = "Tid") Long Tid, Model model) {
        Ticket ticket = ticketRepo.findById(Tid).get();
        model.addAttribute("ticket", ticket);
        return "purchase";
    }
    //Обработка покупки билета пост запросом
    @PostMapping("/cinemas/{name}/{id}/{Tid}")
    public String purchasePost(@PathVariable(value = "name") String name, @PathVariable(value = "id") Long id, @PathVariable(value = "Tid") Long Tid, @RequestParam String user, Model model) {
        Ticket ticket = ticketRepo.findById(Tid).get();
        if (!userRepo.existsByUsername(user)) {
            User user2 = new User(user);
            userRepo.save(user2);
            user2.setTicket(ticket);
            ticket.setUsers(user2);
            ticket.setReserve(true);
            ticketRepo.save(ticket);
            userRepo.save(user2);
        } else {
        User user1 = userRepo.findByUsername(user);
        user1.setTicket(ticket);
        user1.getTicket().add(ticket);
        userRepo.save(user1);
        ticket.setUsers(user1);
        ticket.setReserve(true);
        ticketRepo.save(ticket);
        }
        return "redirect:/cinemas/{name}/{id}";
    }
}
