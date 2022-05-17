package com.example.cinemahain.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long hallNum;

    private String film, date;

    @OneToMany( cascade = {CascadeType.ALL})
    private Set<Ticket> tickets;

    @ManyToOne
    private Cinemas cinemas;

    public Seance() {
    }

    public Seance(long hallNum, String film, String date, Cinemas cinemas) {
        this.hallNum = hallNum;
        this.film = film;
        this.date = date;
        this.cinemas = cinemas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHallNum() {
        return hallNum;
    }

    public void setHallNum(long hallNum) {
        this.hallNum = hallNum;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
    public void setTickets(Ticket tickets) {
        this.tickets = Collections.singleton(tickets);
    }
    public Cinemas getCinemas() {
        return cinemas;
    }

    public void setCinemas(Cinemas cinemas) {
        this.cinemas = cinemas;
    }
}
