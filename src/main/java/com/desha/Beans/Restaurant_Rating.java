package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by Nenad on 2/28/2017.
 */
@Entity
@IdClass(Restaurant_RatingKey.class)
public class Restaurant_Rating {
    @Id
    @Column(name ="restaurant_name" )
    private String restaurantName;

    @Id
    @Column(name ="guest_email" )
    private String guestEmail;

    private int rating;

    public Restaurant_Rating() {
    }

    public Restaurant_Rating(String restaurantName, String guestEmail, int rating) {
        this.restaurantName = restaurantName;
        this.guestEmail = guestEmail;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
