package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by Nenad on 2/28/2017.
 */
@Entity
@IdClass(Employee_RatingKey.class)
public class Employee_Rating {
    @Id
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Id
    @Column(name = "employee_email")
    private String employeeEmail;
    @Id
    @Column(name = "guest_email")
    private String guestEmail;

    private int rating;

    public Employee_Rating() {
    }

    public Employee_Rating(String restaurantName, String employeeEmail, String guestEmail, int rating) {
        this.restaurantName = restaurantName;
        this.employeeEmail = employeeEmail;
        this.guestEmail = guestEmail;
        this.rating = rating;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
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
