package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by Nenad on 2/25/2017.
 */
@Entity
@IdClass(Restaurant_ManagerKey.class)
public class Restaurant_Manager {
    @Id
    @Column(name = "manager_email")
    private String managerEmail;
    @Id
    @Column(name = "restaurant_name")
    private String restaurantName;

    public Restaurant_Manager() {
    }

    public Restaurant_Manager(String managerEmail, String restaurantName) {
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
