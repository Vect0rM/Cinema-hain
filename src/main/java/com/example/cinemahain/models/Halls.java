package com.example.cinemahain.models;

import javax.persistence.*;
import java.util.Set;
//Модель представления данных залов для бд
@Entity
public class Halls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long num;
    //Связь с конотеатрами
    @ManyToOne
    private Cinemas cinemas;

    public Halls() {
    }

    public Halls(long num, Cinemas cinemas) {
        this.num = num;
        this.cinemas = cinemas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public Cinemas getCinemas() {
        return cinemas;
    }

    public void setCinemas(Cinemas cinemas) {
        this.cinemas = cinemas;
    }
}
