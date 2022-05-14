package com.example.cinemahain.controller;

import com.example.cinemahain.models.Ticket;
import com.example.cinemahain.models.User;
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

@Controller
public class TicketsController {

    private final TicketRepo ticketRepo;
    private final UserRepo userRepo;

    public TicketsController(TicketRepo ticketRepo, UserRepo userRepo) {
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping("/cinemas/{name}/{id}")
    public String tickets(@PathVariable(value = "name") String name, @PathVariable(value = "id") Long id, Model model) {
        Iterable<Ticket> tickets = ticketRepo.findTicketBySeanceId(id);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }
    @GetMapping("/cinemas/{name}/{id}/{Tid}")
    public String purchase(@PathVariable(value = "name") String name, @PathVariable(value = "id") Long id, @PathVariable(value = "Tid") Long Tid, Model model) {
        Ticket ticket = ticketRepo.findById(Tid).get();
        model.addAttribute("ticket", ticket);
        return "purchase";
    }
    @PostMapping("/cinemas/{name}/{id}/{Tid}")
    public String purchasePost(@PathVariable(value = "name") String name, @PathVariable(value = "id") Long id, @PathVariable(value = "Tid") Long Tid, @RequestParam String user, Model model) {
        Ticket ticket = ticketRepo.findById(Tid).get();
        if (!userRepo.existsByUsername(user)) {
            User user2 = new User(user);
            user2.setTicket(ticket);
            user2.getTicket().add(ticket);
            ticket.setUsers(user2);
            ticket.setReserve(true);
            userRepo.save(user2);
            ticketRepo.save(ticket);
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
