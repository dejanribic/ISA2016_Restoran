package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by Nenad on 2/28/2017.
 */
@Entity
@IdClass(Menu_Item_RatingKey.class)
public class Menu_Item_Rating {
    @Id
    @Column(name = "menu_item_name")
    private String menuItemName;
    @Id
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Id
    @Column(name = "guest_email")
    private String guestEmail;

    private int rating;

    public Menu_Item_Rating() {
    }

    public Menu_Item_Rating(String menuItemName, String restaurantName, String guestEmail, int rating) {
        this.menuItemName = menuItemName;
        this.restaurantName = restaurantName;
        this.guestEmail = guestEmail;
        this.rating = rating;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
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
