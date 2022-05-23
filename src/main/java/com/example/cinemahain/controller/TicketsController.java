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

import java.util.ArrayList;

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
    private ArrayList<Ticket> ticketsCart = new ArrayList<>();
    private ArrayList<Ticket> ticketsSuccess = new ArrayList<>();

    //Страница с выбором билета
    @GetMapping("/cinemas/{name}/{id}")
    public String tickets(@PathVariable(value = "name") String name, @PathVariable(value = "id") Long id, Model model) {
        ticketsCart = new ArrayList<>();
        Seance seance = seanceRepo.findById(id).get();
        Films films = seance.getFilms();
        Iterable<Ticket> tickets = ticketRepo.findTicketBySeanceId(id);
        model.addAttribute("film", films);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }
    @PostMapping("/cinemas/{name}/{id}")
    public String ticketsPost(@PathVariable(value = "name") String name, @PathVariable(value = "id") Long id,@RequestParam(defaultValue = "-1") Long[] ticket ,Model model) {
        ticketsCart = new ArrayList<>();
        for (Long t:
             ticket) {
            if (t != null){
                ticketsCart.add(ticketRepo.findTicketBySeanceIdAndPlace(id, t));
            }
        }
        if (ticketsCart.size() == 0) {
            return "redirect:/cinemas/{name}/{id}";
        }
        return "redirect:/cinemas/purchase/buy";
    }
    //Страница с покупкой билета
    @GetMapping("/cinemas/purchase/buy")
    public String purchase(Model model) {
        ticketsSuccess = new ArrayList<>(ticketsCart);
        Ticket mainTicket = ticketsCart.get(0);
        ticketsCart.remove(0);
        model.addAttribute("tickMain", mainTicket);
        if (ticketsCart.size() >= 1) {
            model.addAttribute("tick", ticketsCart);
        }
        return "purchase";
    }
    //Обработка покупки билета пост запросом
    @PostMapping("/cinemas/purchase/buy")
    public String purchasePost(@RequestParam(defaultValue = "-1") String user) {
        User user1;
        int check = 0;
        if (ticketsSuccess.size() > 0) {
            ticketsCart.add(ticketsSuccess.get(0));
        for (Ticket t:
             ticketsSuccess) {
            if (t.isReserve()) {
                check++;
            }
        }
        if (!user.equals("-1") && check == 0) {
        if (!userRepo.existsByUsername(user)) {
            user1 = new User(user);
            userRepo.save(user1);
            for (Ticket ticket:
                 ticketsSuccess) {
            user1.setTicket(ticket);
            ticket.setUsers(user1);
            ticket.setReserve(true);
            ticketRepo.save(ticket);
            userRepo.save(user1);
            ticketsCart.remove(ticket);
            }
        } else {
            user1 = userRepo.findByUsername(user);
            for (Ticket ticket:
                 ticketsSuccess) {
        user1.setTicket(ticket);
        user1.getTicket().add(ticket);
        userRepo.save(user1);
        ticket.setUsers(user1);
        ticket.setReserve(true);
        ticketRepo.save(ticket);
        ticketsCart.remove(ticket);
            }
        }
        return "redirect:/cinemas/purchase/success";
        }
        return "redirect:/cinemas";
        }
        return "redirect:/cinemas";
    }
    @GetMapping("/cinemas/purchase/success")
    public String success(Model model) {
        model.addAttribute("success", ticketsSuccess);
        return "success";
    }
    @PostMapping("/cinemas/purchase/success")
    public String successPost(Model model) {
        ticketsSuccess = new ArrayList<>();
        return "redirect:/cinemas";
    }
}
