package com.example.cinemahain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//Модель представления данных акций для бд
@Entity
public class Promotions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name, text, photoSrc;

    public void setId(long id) {
        this.id = id;
    }

    public Promotions(String name, String text, String photoSrc) {
        this.name = name;
        this.text = text;
        this.photoSrc = photoSrc;
    }

    public String getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Promotions(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public Promotions() {

    }
}
