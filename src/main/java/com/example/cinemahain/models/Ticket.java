package com.example.cinemahain.models;

import javax.persistence.*;
//Модель представления данных билетов для бд
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long price;

    private boolean reserve;

    private long place;
    //Связь с сеансами
    @ManyToOne
    private Seance seance;
    //Связь с пользователями
    @ManyToOne
    private User users;

    public Ticket() {
    }

    public Ticket(long price, boolean reserve, long place, Seance seance) {
        this.price = price;
        this.reserve = reserve;
        this.place = place;
        this.seance = seance;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    public long getPlace() {
        return place;
    }

    public void setPlace(long place) {
        this.place = place;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
