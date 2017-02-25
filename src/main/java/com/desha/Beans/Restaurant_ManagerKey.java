package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Nenad on 2/25/2017.
 */
@Embeddable
public class Restaurant_ManagerKey implements Serializable {
    @Id
    @Column(name = "manager_email")
    private String managerEmail;
    @Id
    @Column(name = "restaurant_name")
    private String restaurantName;

    public Restaurant_ManagerKey() {
    }

    public Restaurant_ManagerKey(String managerEmail, String restaurantName) {
        this.managerEmail = managerEmail;
        this.restaurantName = restaurantName;


    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String email) {
        this.managerEmail = managerEmail;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
