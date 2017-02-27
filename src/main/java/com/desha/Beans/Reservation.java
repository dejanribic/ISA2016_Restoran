package com.desha.Beans;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@IdClass(ReservationKey.class)
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Id
    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column
    private Timestamp start;

    @Column
    private int forh;

    // Strani kljuc iz guest
    @Column(name = "guest_email")
    private String guestEmail;

    public Reservation() {
    }

    public Reservation(int id, String restaurant_name, Timestamp start, int forh, String guestEmail) {
        this.id = id;
        this.restaurantName = restaurant_name;
        this.start = start;
        this.forh = forh;
        this.guestEmail = guestEmail;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", restaurantName='" + restaurantName + '\'' +
                ", start=" + start +
                ", forh=" + forh +
                ", guestEmail='" + guestEmail + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public int getForh() {
        return forh;
    }

    public void setForh(int forh) {
        this.forh = forh;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }
}
