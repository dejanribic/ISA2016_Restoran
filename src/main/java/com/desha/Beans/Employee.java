package com.desha.Beans;




import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Viktor on 2/26/2017.
 */


@Entity
@IdClass(EmployeeKey.class)
public class Employee implements Serializable {


    @Id
    private String email;

    @Id
    @Column(name =  "restaurant_name")
    private String restaurantName;

    @Column
    private String password;

    @Column
    private int size;

    @Column
    private int shoe;

    @Column
    private Date birthday;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private Boolean firstlog;


    public Employee() {
    }

    public Employee(String email, String restaurantName, String password, int size, int shoe, Date birthday, String name, String surname, Boolean firstlog) {
        this.email = email;
        this.restaurantName = restaurantName;
        this.password = password;
        this.size = size;
        this.shoe = shoe;
        this.birthday = birthday;
        this.name = name;
        this.surname = surname;
        this.firstlog = firstlog;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getShoe() {
        return shoe;
    }

    public void setShoe(int shoe) {
        this.shoe = shoe;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getFirstlog() {
        return firstlog;
    }

    public void setFirstlog(Boolean firstlog) {
        this.firstlog = firstlog;
    }
}
