package com.desha.Beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Nenad on 2/22/2017.
 */
@Entity
@IdClass(Menu_ItemKey.class)
public class Menu_Item implements Serializable{

    @Id
    private String name;

    private String description;

    private int price;

    //@JoinColumn(foreignKey = @ForeignKey(name = "fk_Menu_Item_Menu_Item_Type1"))
    private String type_name;

    @Id
    @Column(name = "restaurant_name")
    private String restname;

    public Menu_Item(){

    }

    public Menu_Item(String name, String description, int price, String type_name, String restaurant_name) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type_name = type_name;
        this.restname = restaurant_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getRestname() {
        return restname;
    }

    public void setRestname(String restname) {
        this.restname = restname;
    }
}


