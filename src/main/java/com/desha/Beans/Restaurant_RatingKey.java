package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Nenad on 2/28/2017.
 */
@Embeddable
public class Restaurant_RatingKey implements Serializable {
    @Id
    @Column(name ="restaurant_name" )
    private String restaurantName;

    @Id
    @Column(name ="guest_email" )
    private String guestEmail;

    public Restaurant_RatingKey() {
    }

    public Restaurant_RatingKey(String restaurantName, String guestEmail) {
        this.restaurantName = restaurantName;
        this.guestEmail = guestEmail;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }
}
