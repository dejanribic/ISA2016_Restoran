package com.desha.Beans;

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
    private String restaurant_name;

    public EmployeeKey( String email , String restaurant_name)
    {
        this.email = email;
        this.restaurant_name=restaurant_name;
    }

    public EmployeeKey(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }
}
