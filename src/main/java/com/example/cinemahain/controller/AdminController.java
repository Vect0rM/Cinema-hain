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

import java.util.Collections;
import java.util.Set;

//Контроллер панели администратора
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
    final private GenresRepo genresRepo;
    //Конструктор с репозиториями
    public AdminController(FilmsRepo filmsRepo, PromotionsRepo promotionsRepo, UserRepo userRepo, TicketRepo ticketRepo, SeanceRepo seanceRepo, WorkersRepo workersRepo, CinemasRepo cinemasRepo, HallsRepo hallsRepo, GenresRepo genresRepo) {
        this.filmsRepo = filmsRepo;
        this.promotionsRepo = promotionsRepo;
        this.userRepo = userRepo;
        this.ticketRepo = ticketRepo;
        this.seanceRepo = seanceRepo;
        this.workersRepo = workersRepo;
        this.cinemasRepo = cinemasRepo;
        this.hallsRepo = hallsRepo;
        this.genresRepo = genresRepo;
    }
    //Страница админ панели
    @GetMapping("/admin")
    public String adminPanel() {
        return "admin";
    }
    //Страница редактирования фильмов
    @GetMapping("/admin/films")
    public String adminPanelFilms(Model model) {
        Iterable<Films> films = filmsRepo.findAll();
        model.addAttribute("films", films);
        return "adminPanel/adminFilms";
    }
    //Редактирование/добавление фильма пост запросом
    @PostMapping("/admin/films")
    public String adminPanelFilmsPost(@RequestParam String id, @RequestParam String name, @RequestParam String photoSrc, @RequestParam String text, @RequestParam String genre) {
        Films film;
        if (id.isEmpty()) {
            Genres genres = genresRepo.findById(Long.parseLong(genre)).get();
            film = new Films(name, text, photoSrc, genres);
            genres.getFilms().add(film);
            genresRepo.save(genres);
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
            if (!genre.isEmpty()) {
                Genres genres = genresRepo.findById(Long.parseLong(genre)).get();
                if (film.getGenres() != null) {
                    Genres genres1 = film.getGenres();
                    genres1.getFilms().remove(film);
                    genresRepo.save(genres1);
                }
                film.setGenres(genres);
                genres.getFilms().add(film);
                genresRepo.save(genres);
            }
        }
        filmsRepo.save(film);
        return "redirect:/admin";
    }
    //Удаление фильма пост запросом
    @PostMapping("/admin/films/remove")
    public String adminPanelFilmsRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Films film = filmsRepo.findById(Long.valueOf(id)).get();
            Genres genres = film.getGenres();
            genres.getFilms().remove(film);
            genresRepo.save(genres);
            filmsRepo.delete(film);
        }
        return "redirect:/admin";
    }
    //Страница редактирования акций
    @GetMapping("/admin/promotions")
    public String adminPanelPromotions(Model model) {
        Iterable<Promotions> promotions = promotionsRepo.findAll();
        model.addAttribute("promotions", promotions);
        return "adminPanel/adminPromotions";
    }
    //Редактирование/добавление акций пост запросом
    @PostMapping("/admin/promotions")
    public String adminPanelPromotionsPost(@RequestParam String id, @RequestParam String name, @RequestParam String text, @RequestParam String photo) {
        Promotions promotions;
        if (id.isEmpty()) {
            promotions = new Promotions(name, text, photo);
        } else {
            promotions = promotionsRepo.findById(Long.valueOf(id)).get();
            if (!name.isEmpty()) {
                promotions.setName(name);
            }
            if (!text.isEmpty()) {
                promotions.setText(text);
            }
            if (!photo.isEmpty()) {
                promotions.setPhotoSrc(photo);
            }
        }
        promotionsRepo.save(promotions);
        return "redirect:/admin";
    }
    //Удаление акций пост запросом
    @PostMapping("/admin/promotions/remove")
    public String adminPanelPromotionsRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Promotions promotions = promotionsRepo.findById(Long.valueOf(id)).get();
            promotionsRepo.delete(promotions);
        }
        return "redirect:/admin";
    }
    //Страница выдачи пользователей
    @GetMapping("/admin/users")
    public String adminPanelUsers(Model model) {
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "adminPanel/adminUsers";
    }
    //Страница выдачи проданных билетов
    @GetMapping("/admin/tickets")
    public String adminPanelTickets(Model model) {
        Iterable<Ticket> tickets = ticketRepo.findAll();
        model.addAttribute("tickets", tickets);
        return "adminPanel/adminTickets";
    }
    //Страница редактирования сотрудников
    @GetMapping("/admin/workers")
    public String adminPanelWorkers(Model model) {
        Iterable<Workers> workers = workersRepo.findAll();
        model.addAttribute("workers", workers);
        return "adminPanel/adminWorkers";
    }
    //Редактирование/добавление сотрудников пост запросом
    @PostMapping("/admin/workers")
    public String adminPanelWorkersPost(@RequestParam String id, @RequestParam String name, @RequestParam String photoSrc, @RequestParam String email, @RequestParam String pass, @RequestParam String cinema) {
        Workers workers;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (id.isEmpty()) {
            Cinemas cinemas = cinemasRepo.findById(Long.valueOf(cinema)).get();
           workers = new Workers(name, photoSrc, email, passwordEncoder.encode(pass), cinemas);
           workersRepo.save(workers);
           cinemas.getWorkers().add(workers);
           cinemasRepo.save(cinemas);
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
                cinemas.getWorkers().add(workers);
                cinemasRepo.save(cinemas);
            }
        }
        workersRepo.save(workers);
        return "redirect:/admin";
    }
    //Удаление сотрудника пост запросом
    @PostMapping("/admin/workers/remove")
    public String adminPanelWorkersRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
           Workers workers = workersRepo.findById(Long.valueOf(id)).get();
           Cinemas cinemas = workers.getCinemas();
           cinemas.getWorkers().remove(workers);
           cinemasRepo.save(cinemas);
           workersRepo.delete(workers);
        }
        return "redirect:/admin";
    }
    //Страница редактирования залов
    @GetMapping("/admin/halls")
    public String adminPanelHalls(Model model) {
        Iterable<Halls> halls = hallsRepo.findAll();
        model.addAttribute("halls", halls);
        return "adminPanel/adminHalls";
    }
    //Редактирование/добавление залов пост запросом
    @PostMapping("/admin/halls")
    public String adminPanelHallsPost(@RequestParam String id, @RequestParam String num, @RequestParam String cinema) {
        Halls halls;
        if (id.isEmpty()) {
            Cinemas cinemas = cinemasRepo.findById(Long.valueOf(cinema)).get();
            halls = new Halls(Long.parseLong(num), cinemas);
            hallsRepo.save(halls);
            cinemas.getHalls().add(halls);
            cinemasRepo.save(cinemas);
        } else {
            halls = hallsRepo.findById(Long.valueOf(id)).get();
            if (!num.isEmpty()) {
                halls.setNum(Long.parseLong(num));
            }

            if (!cinema.isEmpty()) {
                Cinemas cinemas = cinemasRepo.findById(Long.valueOf(cinema)).get();
                halls.setCinemas(cinemas);
                cinemas.getHalls().add(halls);
                cinemasRepo.save(cinemas);
            }
        }
        hallsRepo.save(halls);
        return "redirect:/admin";
    }
    //Удаление залов пост запросом
    @PostMapping("/admin/halls/remove")
    public String adminPanelHallsRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Halls halls = hallsRepo.findById(Long.valueOf(id)).get();
            Cinemas cinemas = halls.getCinemas();
            cinemas.getHalls().remove(halls);
            cinemasRepo.save(cinemas);
            hallsRepo.delete(halls);
        }
        return "redirect:/admin";
    }
    //Страница редактирования кинотеатров
    @GetMapping("/admin/cinemas")
    public String adminPanelCinemas(Model model) {
        Iterable<Cinemas> cinemas = cinemasRepo.findAll();
        model.addAttribute("cinemas", cinemas);
        return "adminPanel/adminCinemas";
    }
    //Редактирование/добавление кинотеатров пост запросом
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
    //Добавить залы кинотеатру пост запросом
    @PostMapping("/admin/cinemas/add")
    public String AdminPanelCinemaAdd(@RequestParam String id,@RequestParam String hall){
        if (!id.isEmpty()) {
            Cinemas cinemas = cinemasRepo.findById(Long.parseLong(id)).get();
            if (!hall.isEmpty()) {
                for (int i = 1; i < Integer.parseInt(hall) + 1; i++) {
                    Halls halls = new Halls(i, cinemas);
                    hallsRepo.save(halls);
                    cinemas.getHalls().add(halls);
                }
            }
            cinemasRepo.save(cinemas);
        }
        return "redirect:/admin";
    }
    //Удаление кмнотеатра пост запросом
    @PostMapping("/admin/cinemas/remove")
    public String adminPanelCinemasRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Cinemas cinemas = cinemasRepo.findById(Long.valueOf(id)).get();
            Set <Halls> halls = cinemas.getHalls();
            Set<Workers> workers = cinemas.getWorkers();
            Set<Seance> seances = cinemas.getSeances();
            cinemas.getSeances().removeAll(seances);
            cinemas.getWorkers().removeAll(workers);
            cinemas.getHalls().removeAll(halls);
            for (Seance s:
                 seances) {
                Set<Ticket> tickets = s.getTickets();
                for (Ticket t:
                     tickets) {
                    User user = t.getUsers();
                    user.getTicket().remove(t);
                    userRepo.save(user);
                    ticketRepo.delete(t);
                }
            }
            seanceRepo.deleteAll(seanceRepo.findByCinemas_Name(cinemas.getName()));
            hallsRepo.deleteAll(hallsRepo.findHallsByCinemasId(cinemas.getId()));
            workersRepo.deleteAll(workersRepo.findWorkersByCinemasId(cinemas.getId()));
            cinemasRepo.delete(cinemas);
        }
        return "redirect:/admin";
    }
    //Страница редактирования сеансов
    @GetMapping("/admin/seances")
    public String adminPanelSeances(Model model) {
        Iterable<Seance> seances = seanceRepo.findAll();
        model.addAttribute("seances", seances);
        return "adminPanel/adminSeances";
    }
    //Редактирование/добавление сеансов пост запросом
    @PostMapping("/admin/seances")
    public String adminPanelSeancePost(@RequestParam String id, @RequestParam String cinema, @RequestParam String date, @RequestParam String film, @RequestParam String hall) {
        Seance seance;
        if (id.isEmpty()) {
            Cinemas cinemas = cinemasRepo.findById(Long.parseLong(cinema)).get();
            Films films = filmsRepo.findById(Long.parseLong(film)).get();
            seance = new Seance(Long.parseLong(hall), date, cinemas, films);
            seanceRepo.save(seance);
            films.getSeances().add(seance);
            cinemas.getSeances().add(seance);
            cinemasRepo.save(cinemas);
            filmsRepo.save(films);
        } else {
            seance = seanceRepo.findById(Long.valueOf(id)).get();
            if (!cinema.isEmpty()) {
                Cinemas cinemas = cinemasRepo.findById(Long.parseLong(cinema)).get();
                seance.setCinemas(cinemas);
                cinemas.getSeances().add(seance);
                cinemasRepo.save(cinemas);
            }
            if (!date.isEmpty()) {
                seance.setDate(date);
            }
            if (!film.isEmpty()) {
                Films films = filmsRepo.findById(Long.parseLong(film)).get();
                seance.setFilms(films);
                films.getSeances().add(seance);
                filmsRepo.save(films);
            }
            if (!hall.isEmpty()) {
                seance.setHallNum(Long.parseLong(hall));
            }
        }
        seanceRepo.save(seance);
        return "redirect:/admin";
    }
    //добавить билеты сеансу пост запросом
    @PostMapping("/admin/seances/add")
    public String AdminPanelSeanceAdd(@RequestParam String id, @RequestParam String prise){
        if (!prise.isEmpty() && !id.isEmpty()) {
            Seance seance = seanceRepo.findById(Long.valueOf(id)).get();
            for (int i = 1; i < 26; i++) {
                Ticket ticket2 = new Ticket(Long.parseLong(prise), false, i, seance);
                seance.getTickets().add(ticket2);
            }
            seanceRepo.save(seance);
        }
        return "redirect:/admin";
    }
    //Удаление фильма сеанса запросом
    @PostMapping("/admin/seances/remove")
    public String adminPanelSeanceRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Seance seance = seanceRepo.findById(Long.valueOf(id)).get();
            Cinemas cinemas = seance.getCinemas();
            cinemas.getSeances().remove(seance);
            Films films = seance.getFilms();
            films.getSeances().remove(seance);
            filmsRepo.save(films);
            cinemasRepo.save(cinemas);
            Set<Ticket> tickets = seance.getTickets();
            for (Ticket t:
                 tickets) {
                if (t.isReserve()) {
                    User user = t.getUsers();
                    user.getTicket().remove(t);
                    userRepo.save(user);
                }
            }
            seance.getTickets().removeAll(Collections.singleton(ticketRepo.findTicketBySeanceId(Long.parseLong(id))));
            ticketRepo.deleteAll(tickets);
            seanceRepo.delete(seance);
        }
        return "redirect:/admin";
    }
    @GetMapping("/admin/genres")
    public String adminPanelGenres(Model model) {
        Iterable<Genres> genres = genresRepo.findAll();
        model.addAttribute("genres", genres);
        return "adminPanel/adminGenres";
    }
    @PostMapping("/admin/genres")
    public String adminPanelGenresPost(@RequestParam String id, @RequestParam String name) {
        Genres genres;
        if (id.isEmpty()) {
            genres = new Genres(name);
        } else {
            genres = genresRepo.findById(Long.valueOf(id)).get();
            if (!name.isEmpty()) {
                genres.setName(name);
            }
        }
        genresRepo.save(genres);
        return "redirect:/admin";
    }
    @PostMapping("/admin/genres/remove")
    public String adminPanelGenresRemove(@RequestParam String id) {
        if (!id.isEmpty()) {
            Genres genres = genresRepo.findById(Long.parseLong(id)).get();
            genresRepo.delete(genres);
        }
        return "redirect:/admin";
    }
}
