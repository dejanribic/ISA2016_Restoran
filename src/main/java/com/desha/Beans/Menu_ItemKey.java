package com.desha.Beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Nenad on 2/22/2017.
 */
@Embeddable
public class Menu_ItemKey implements Serializable {
    @Id
    private String name;

    @Id
    @Column(name = "restaurant_name")
    private String restname;

    public Menu_ItemKey(String name, String restaurant_name) {
        this.name = name;
        this.restname = restaurant_name;
    }

    public Menu_ItemKey() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getRestname() {
        return restname;
    }

    public void setRestname(String restname) {
        this.restname = restname;
    }
}