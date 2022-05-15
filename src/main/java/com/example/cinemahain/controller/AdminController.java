package com.example.cinemahain.controller;

import com.example.cinemahain.models.*;
import com.example.cinemahain.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminController {

    final private FilmsRepo filmsRepo;
    final private PromotionsRepo promotionsRepo;
    final private UserRepo userRepo;
    final private TicketRepo ticketRepo;
    final private SeanceRepo seanceRepo;
    final private WorkersRepo workersRepo;
    final private CinemasRepo cinemasRepo;
    final private HallsRepo hallsRepo;

    public AdminController(FilmsRepo filmsRepo, PromotionsRepo promotionsRepo, UserRepo userRepo, TicketRepo ticketRepo, SeanceRepo seanceRepo, WorkersRepo workersRepo, CinemasRepo cinemasRepo, HallsRepo hallsRepo) {
        this.filmsRepo = filmsRepo;
        this.promotionsRepo = promotionsRepo;
        this.userRepo = userRepo;
        this.ticketRepo = ticketRepo;
        this.seanceRepo = seanceRepo;
        this.workersRepo = workersRepo;
        this.cinemasRepo = cinemasRepo;
        this.hallsRepo = hallsRepo;
    }

    @GetMapping("/admin")
    public String adminPanel() {
        return "admin";
    }
    @GetMapping("/admin/films")
    public String adminPanelFilms(Model model) {
        Iterable<Films> films = filmsRepo.findAll();
        model.addAttribute("films", films);
        return "adminPanel/adminFilms";
    }
    @PostMapping("/admin/films")
    public String adminPanelFilmsPost(@RequestParam String id, @RequestParam String name, @RequestParam String photoSrc, @RequestParam String text) {
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
    @PostMapping("/admin/films/remove")
    public String adminPanelFilmsRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Films film = filmsRepo.findById(Long.valueOf(id)).get();
            filmsRepo.delete(film);
        }
        return "redirect:/admin";
    }
    @GetMapping("/admin/promotions")
    public String adminPanelPromotions(Model model) {
        Iterable<Promotions> promotions = promotionsRepo.findAll();
        model.addAttribute("promotions", promotions);
        return "adminPanel/adminPromotions";
    }
    @PostMapping("/admin/promotions")
    public String adminPanelPromotionsPost(@RequestParam String id, @RequestParam String name, @RequestParam String text) {
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

    @PostMapping("/admin/promotions/remove")
    public String adminPanelPromotionsRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Promotions promotions = promotionsRepo.findById(Long.valueOf(id)).get();
            promotionsRepo.delete(promotions);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/users")
    public String adminPanelUsers(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "adminPanel/adminUsers";
    }
    @GetMapping("/admin/tickets")
    public String adminPanelTickets(Model model) {
        Iterable<Ticket> tickets = ticketRepo.findAll();
        model.addAttribute("tickets", tickets);
        return "adminPanel/adminTickets";
    }
    @PostMapping("/admin/tickets")
    public String adminPanelTicketsPost(@RequestParam String prise, @RequestParam String place, @RequestParam String seance) {

           Ticket ticket = new Ticket();
            if (!prise.isEmpty()) {
                ticket.setPrice(Long.parseLong(prise));
            }
            if (!place.isEmpty()) {
                ticket.setPlace(Long.parseLong(place));
            }
            if (!seance.isEmpty()) {
               Seance seance1 = seanceRepo.findById(Long.valueOf(seance)).get();
               ticket.setSeance(seance1);
            }
            ticket.setReserve(false);
            ticketRepo.save(ticket);
        return "redirect:/admin";
    }
    @PostMapping("/admin/tickets/remove")
    public String adminPanelTicketsRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Ticket ticket = ticketRepo.findById(Long.valueOf(id)).get();
            ticketRepo.delete(ticket);
        }
        return "redirect:/admin";
    }
    @GetMapping("/admin/workers")
    public String adminPanelWorkers(Model model) {
        Iterable<Workers> workers = workersRepo.findAll();
        model.addAttribute("workers", workers);
        return "adminPanel/adminWorkers";
    }
    @PostMapping("/admin/workers")
    public String adminPanelWorkersPost(@RequestParam String id, @RequestParam String name, @RequestParam String photoSrc, @RequestParam String email, @RequestParam String pass, @RequestParam String cinema) {
        Workers workers;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (id.isEmpty()) {
            Cinemas cinemas = cinemasRepo.findById(Long.valueOf(cinema)).get();
           workers = new Workers(name, photoSrc, email, passwordEncoder.encode(pass), cinemas);
        } else {
            workers = workersRepo.findById(Long.valueOf(id)).get();
            if (!name.isEmpty()) {
                workers.setName(name);
            }
            if (!photoSrc.isEmpty()) {
                workers.setPhotoSrc(photoSrc);
            }
            if (!email.isEmpty()) {
                workers.setEmail(email);
            }
            if (!pass.isEmpty()) {
                workers.setPassword(passwordEncoder.encode(pass));
            }
            if (!cinema.isEmpty()) {
                Cinemas cinemas = cinemasRepo.findById(Long.valueOf(cinema)).get();
                workers.setCinemas(cinemas);
            }
        }
        workersRepo.save(workers);
        return "redirect:/admin";
    }
    @PostMapping("/admin/workers/remove")
    public String adminPanelWorkersRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Workers workers = workersRepo.findById(Long.valueOf(id)).get();
            workersRepo.delete(workers);
        }
        return "redirect:/admin";
    }
    @GetMapping("/admin/halls")
    public String adminPanelHalls(Model model) {
        Iterable<Halls> halls = hallsRepo.findAll();
        model.addAttribute("halls", halls);
        return "adminPanel/adminHalls";
    }
    @PostMapping("/admin/halls")
    public String adminPanelHallsPost(@RequestParam String id, @RequestParam String num, @RequestParam String cinema) {
        Halls halls;
        if (id.isEmpty()) {
            Cinemas cinemas = cinemasRepo.findById(Long.valueOf(cinema)).get();
            halls = new Halls(Long.parseLong(num), cinemas);
        } else {
            halls = hallsRepo.findById(Long.valueOf(id)).get();
            if (!num.isEmpty()) {
                halls.setNum(Long.parseLong(num));
            }

            if (!cinema.isEmpty()) {
                Cinemas cinemas = cinemasRepo.findById(Long.valueOf(cinema)).get();
                halls.setCinemas(cinemas);
            }
        }
        hallsRepo.save(halls);
        return "redirect:/admin";
    }
    @PostMapping("/admin/halls/remove")
    public String adminPanelHallsRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Halls halls = hallsRepo.findById(Long.valueOf(id)).get();
            hallsRepo.delete(halls);
        }
        return "redirect:/admin";
    }
    @GetMapping("/admin/cinemas")
    public String adminPanelCinemas(Model model) {
        Iterable<Cinemas> cinemas = cinemasRepo.findAll();
        model.addAttribute("cinemas", cinemas);
        return "adminPanel/adminCinemas";
    }
    @PostMapping("/admin/cinemas")
    public String adminPanelCinemasPost(@RequestParam String id, @RequestParam String name, @RequestParam String photo, @RequestParam String text,  @RequestParam String city) {
        Cinemas cinemas;
        if (id.isEmpty()) {
            cinemas = new Cinemas(name, photo, city, text);
        } else {
            cinemas = cinemasRepo.findById(Long.valueOf(id)).get();
            if (!name.isEmpty()) {
                cinemas.setName(name);
            }
            if (!photo.isEmpty()) {
                cinemas.setPhoto(photo);
            }
            if (!text.isEmpty()) {
                cinemas.setText(text);
            }
        }
        cinemasRepo.save(cinemas);
        return "redirect:/admin";
    }
    @PostMapping("/admin/cinema/add")
    public String AdminPanelCinemaAdd(@RequestParam String id,@RequestParam String hall, @RequestParam String worker,  @RequestParam String seance){
        if (id.isEmpty()) {
            Cinemas cinemas = cinemasRepo.findById(Long.parseLong(id)).get();
            if (!hall.isEmpty()) {
                Halls halls = hallsRepo.findById(Long.parseLong(hall)).get();
                cinemas.getHalls().add(halls);
            }
            if (!worker.isEmpty()) {
                Workers workers = workersRepo.findById(Long.parseLong(worker)).get();
                cinemas.getWorkers().add(workers);
            }
            if (!seance.isEmpty()) {
                Seance seance1 = seanceRepo.findById(Long.parseLong(seance)).get();
                cinemas.getSeances().add(seance1);
            }
            cinemasRepo.save(cinemas);
        }
        return "redirect:/admin";
    }
    @PostMapping("/admin/cinemas/remove")
    public String adminPanelCinemasRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Cinemas cinemas = cinemasRepo.findById(Long.valueOf(id)).get();
            cinemasRepo.delete(cinemas);
        }
        return "redirect:/admin";
    }
    @GetMapping("/admin/seances")
    public String adminPanelSeances(Model model) {
        Iterable<Seance> seances = seanceRepo.findAll();
        model.addAttribute("seances", seances);
        return "adminPanel/adminSeances";
    }
    @PostMapping("/admin/seances")
    public String adminPanelSeancePost(@RequestParam String id, @RequestParam String cinema, @RequestParam String date, @RequestParam String film, @RequestParam String hall) {
        Seance seance;
        if (id.isEmpty()) {
            Cinemas cinemas = cinemasRepo.findById(Long.parseLong(cinema)).get();
            seance = new Seance(Long.parseLong(hall), film, date, cinemas);
        } else {
            seance = seanceRepo.findById(Long.valueOf(id)).get();
            if (!cinema.isEmpty()) {
                Cinemas cinemas = cinemasRepo.findById(Long.parseLong(cinema)).get();
                seance.setCinemas(cinemas);
            }
            if (!date.isEmpty()) {
                seance.setDate(date);
            }
            if (!film.isEmpty()) {
                seance.setFilm(film);
            }
            if (!hall.isEmpty()) {
                seance.setHallNum(Long.parseLong(hall));
            }
        }
        seanceRepo.save(seance);
        return "redirect:/admin";
    }
    @PostMapping("/admin/seances/add")
    public String AdminPanelSeanceAdd(@RequestParam String id,@RequestParam String amount, @RequestParam String prise){
        if (!amount.isEmpty() && !prise.isEmpty() && !id.isEmpty()) {
            Seance seance = seanceRepo.findById(Long.valueOf(id)).get();
            for (int i = 0; i < Integer.parseInt(amount); i++) {
                Ticket ticket2 = new Ticket(Long.parseLong(prise), false, i, seance);
                seance.getTickets().add(ticket2);
            }
            seanceRepo.save(seance);
        }
        return "redirect:/admin";
    }
    @PostMapping("/admin/seances/remove")
    public String adminPanelSeanceRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Seance seance = seanceRepo.findById(Long.valueOf(id)).get();
            seanceRepo.delete(seance);
        }
        return "redirect:/admin";
    }
}
