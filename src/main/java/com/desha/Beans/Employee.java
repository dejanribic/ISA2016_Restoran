package com.desha.Beans;




import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Viktor on 2/26/2017.
 */


@Entity
@IdClass(EmployeeKey.class)
public class Employee implements Serializable {


    @Id
    private String email;

    @Id
    private String restaurant_name;

    @Column
    private String password;

    public Employee(){}

    public Employee(String email , String restaurant_name , String password)
    {
        this.email= email;
        this.restaurant_name = restaurant_name;
        this.password= password;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
