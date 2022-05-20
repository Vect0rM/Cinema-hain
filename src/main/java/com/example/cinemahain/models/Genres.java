package com.example.cinemahain.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Genres {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany
    private Set<Films> films;

    public Genres() {
    }

    public Genres(String name) {
        this.name = name;
    }


    public Set<Films> getFilms() {
        return films;
    }

    public void setFilms(Set<Films> films) {
        this.films = films;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
