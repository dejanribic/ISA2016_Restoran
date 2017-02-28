package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Nenad on 2/28/2017.
 */
@Embeddable
public class Menu_Item_RatingKey implements Serializable {
    @Id
    @Column(name = "menu_item_name")
    private String menuItemName;
    @Id
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Id
    @Column(name = "guest_email")
    private String guestEmail;

    public Menu_Item_RatingKey() {
    }

    public Menu_Item_RatingKey(String menuItemName, String restaurantName, String guestEmail) {
        this.menuItemName = menuItemName;
        this.restaurantName = restaurantName;
        this.guestEmail = guestEmail;
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
}
