package com.example.cinemahain.controller;

import com.example.cinemahain.models.Ticket;
import com.example.cinemahain.models.User;
import com.example.cinemahain.repository.TicketRepo;
import com.example.cinemahain.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class TicketsController {

    private final TicketRepo ticketRepo;
    private final UserRepo userRepo;

    public TicketsController(TicketRepo ticketRepo, UserRepo userRepo) {
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
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
        User user1 = userRepo.findByUsername(user);
        ticket.setUsers(Collections.singleton(user1));
        ticketRepo.save(ticket);
        return "redirect:/cinemas/{name}/{id}";
    }
}
