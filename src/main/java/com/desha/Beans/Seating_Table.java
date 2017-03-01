package com.desha.Beans;

import javax.persistence.*;

/**
 * Created by Nenad on 3/1/2017.
 */
@Entity
@IdClass(Seating_TableKey.class)
public class Seating_Table {
    @Id
    private int id;
    @Id
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Id
    @Column(name = "region_id")
    private int regionId;

    private int x;

    private int y;

    public Seating_Table() {
    }

    public Seating_Table(int id, String restaurantName, int regionId, int x, int y) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.regionId = regionId;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
