package com.desha.Beans;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Nenad on 2/23/2017.
 */
@Entity
@IdClass(DemandKey.class)
public class Demand {

    private String name;
    @Id
    @Column(name="restaurant_name")
    private String restaurantName;
    @Id
    private int num;

    private int ammount;

    private Date start;

    private Date end;

    private boolean closed;

    public Demand() {
    }

    public Demand(String name, String restaurantName, int num, int ammount, Date start, Date end, boolean closed) {
        this.name = name;
        this.restaurantName = restaurantName;
        this.num = num;
        this.ammount = ammount;
        this.start = start;
        this.end = end;
        this.closed = closed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
