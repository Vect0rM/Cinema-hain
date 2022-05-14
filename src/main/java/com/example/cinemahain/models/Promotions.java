package com.example.cinemahain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Promotions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name, text;

    public void setId(long id) {
        this.id = id;
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
