package com.desha.Beans;

/**
 * Created by DEJAN on 26/02/17 at 16:47.
 */

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class ReservationKey implements Serializable {

    @Id
    @Column
    private int id;

    @Id
    @Column
    private String restaurantName;

    public ReservationKey() {
    }

    public ReservationKey(int id, String restaurant_name) {
        this.id = id;
        this.restaurantName = restaurant_name;
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
}