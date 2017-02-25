package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Nenad on 2/23/2017.
 */
@Embeddable
public class DemandKey implements Serializable {
    @Id
    private int num;

    @Id
    //@JoinColumn(foreignKey = @ForeignKey(name = "fk_Demand_Restaurant1"))
    @Column(name="restaurant_name")
    private String restaurantName;

    public DemandKey(int num, String restaurantName) {
        this.num = num;
        this.restaurantName = restaurantName;
    }

    public DemandKey() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


}

