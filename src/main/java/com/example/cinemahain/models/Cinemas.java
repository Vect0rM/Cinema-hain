package com.example.cinemahain.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cinemas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name, photo, city, text;

    @OneToMany
    private Set<Halls> halls;

    @OneToMany
    private Set<Workers> workers;

    @OneToMany
    private Set<Seance> seances;

    public Cinemas() {
    }

    public Cinemas(String name, String photo, String city, String text, Set<Halls> halls, Set<Workers> workers, Set<Seance> seances) {
        this.name = name;
        this.photo = photo;
        this.city = city;
        this.text = text;
        this.halls = halls;
        this.workers = workers;
        this.seances = seances;
    }

    public Cinemas(String name, String photo, String city, String text) {
        this.name = name;
        this.photo = photo;
        this.city = city;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Halls> getHalls() {
        return halls;
    }

    public void setHalls(Set<Halls> halls) {
        this.halls = halls;
    }

    public Set<Workers> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Workers> workers) {
        this.workers = workers;
    }

    public Set<Seance> getSeances() {
        return seances;
    }

    public void setSeances(Set<Seance> seances) {
        this.seances = seances;
    }
}
