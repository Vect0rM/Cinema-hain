package com.example.cinemahain.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;
//Модель представления данных сеансов для бд
@Entity
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long hallNum;

    private String date;
    //Связь с билетами
    @OneToMany( cascade = {CascadeType.ALL})
    private Set<Ticket> tickets;
    //Связь с кинотеатрами
    @ManyToOne
    private Cinemas cinemas;

    @ManyToOne
    private Films films;

    public Seance(long hallNum, String date, Cinemas cinemas, Films films) {
        this.hallNum = hallNum;
        this.date = date;
        this.cinemas = cinemas;
        this.films = films;
    }

    public Films getFilms() {
        return films;
    }

    public void setFilms(Films films) {
        this.films = films;
    }

    public Seance() {
    }

    public Seance(long hallNum, String film, String date, Cinemas cinemas) {
        this.hallNum = hallNum;
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
