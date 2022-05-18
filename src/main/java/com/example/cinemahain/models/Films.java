package com.example.cinemahain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//Модель представления данных фильмов для бд
@Entity
public class Films {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String text;
    private String photoSrc;

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
