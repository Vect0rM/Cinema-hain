package com.example.cinemahain.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
//Модель представления данных пользователей(имплементированная от UserDetails) для бд
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, message = "Не меньше 5 знаков")
    private String username;
    @Size(min=2, message = "Не меньше 5 знаков")
    private String password;
    @Transient
    private String passwordConfirm;
    //Связь с ролями
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    private String Surname;
    private String name;

    private String email;
    //Связь с билетами
    @OneToMany( orphanRemoval = true)
    private Set<Ticket> ticket;

    private long num;

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, String passwordConfirm, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
    }

    public User(String username, String password, String passwordConfirm, Set<Role> roles, Set<Ticket> ticket, long num) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
        this.ticket = ticket;
        this.num = num;
    }

    public User(String username, String password, String passwordConfirm, Set<Role> roles, Set<Ticket> ticket) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
        this.ticket = ticket;
    }

    public User(String username, String password, String passwordConfirm, Set<Role> roles, String surname, String name, String email, Set<Ticket> ticket, long num) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
        Surname = surname;
        this.name = name;
        this.email = email;
        this.ticket = ticket;
        this.num = num;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(Set<Ticket> ticket) {
        this.ticket = ticket;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public void setTicket(Ticket    ticket) {
    }
}
