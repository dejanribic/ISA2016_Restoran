package com.desha.Beans;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;

/**
 * Created by Nenad on 2/22/2017.
 */
@Embeddable
public class Menu_ItemKey implements Serializable {
    @Id
    private String name;

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_Menu_Item_Restaurant1"))
    private String restaurant_name;

    public Menu_ItemKey(String name, String restaurant_name) {
        this.name = name;
        this.restaurant_name = restaurant_name;
    }

    public Menu_ItemKey(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }


}