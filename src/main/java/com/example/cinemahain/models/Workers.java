package com.example.cinemahain.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Workers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name, photoSrc, email, password;

    @ManyToOne
    private Cinemas cinemas;

    public Workers() {
    }

    public Workers(String name, String photoSrc, String email, String password, Cinemas cinemas) {
        this.name = name;
        this.photoSrc = photoSrc;
        this.email = email;
        this.password = password;
        this.cinemas = cinemas;
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

    public String getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cinemas getCinemas() {
        return cinemas;
    }

    public void setCinemas(Cinemas cinemas) {
        this.cinemas = cinemas;
    }
}
