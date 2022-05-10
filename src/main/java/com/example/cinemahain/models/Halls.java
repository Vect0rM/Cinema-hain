package com.example.cinemahain.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Halls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long num;

    @ManyToOne
    private Cinemas cinemas;

    public Halls() {
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
