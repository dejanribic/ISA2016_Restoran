package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Viktor on 2/26/2017.
 */


@Embeddable
public class EmployeeKey implements Serializable {

    @Id
    private String email;

    @Id
    @Column( name = "restaurant_name")
    private String restaurantName;

    public EmployeeKey( String email , String restaurantName)
    {
        this.email = email;
        this.restaurantName = restaurantName;
    }

    public EmployeeKey(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
