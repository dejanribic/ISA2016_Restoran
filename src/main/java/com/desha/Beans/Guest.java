package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Blob;

@Entity
public class Guest {// extends User {

    @Id
    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private int confirmed;

    @Column(nullable = true)
    private Blob image;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }


/*

    @JsonIgnore
    @ManyToMany
    private List<Guest> friends;

    public List<Guest> getFriends() {
        return friends;
    }

    public void setFriends(List<Guest> friends) {
        this.friends = friends;
    }



    @Column
    private int visits;



    @JsonIgnore
    @ManyToMany
    private List<Guest> requests;

    @Column(nullable = true)
    private Blob image;

    @Column(nullable = true)
    private String adress;

    public List<Guest> getRequests() {
        return requests;
    }

    public void setRequests(List<Guest> requests) {
        this.requests = requests;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    */

}
