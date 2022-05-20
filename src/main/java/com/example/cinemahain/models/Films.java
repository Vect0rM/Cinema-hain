package com.example.cinemahain.models;

import javax.persistence.*;
import java.util.Set;

//Модель представления данных фильмов для бд
@Entity
public class Films {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String text;
    private String photoSrc;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Seance> seances;

    @ManyToOne
    private Genres genres;

    public Films() {
    }

    public Films(long id, String name, String text, String photoSrc) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.photoSrc = photoSrc;
    }

    public Films(String name, String text, String photoSrc) {
        this.name = name;
        this.text = text;
        this.photoSrc = photoSrc;
    }

    public Films(String name, String text, String photoSrc, Set<Seance> seances, Genres genres) {
        this.name = name;
        this.text = text;
        this.photoSrc = photoSrc;
        this.seances = seances;
        this.genres = genres;
    }

    public Films(String name, String text, String photoSrc, Genres genres) {
        this.name = name;
        this.text = text;
        this.photoSrc = photoSrc;
        this.genres = genres;
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

    public Set<Seance> getSeances() {
        return seances;
    }

    public void setSeances(Set<Seance> seances) {
        this.seances = seances;
    }

    public Genres getGenres() {
        return genres;
    }

    public void setGenres(Genres genres) {
        this.genres = genres;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }
}
