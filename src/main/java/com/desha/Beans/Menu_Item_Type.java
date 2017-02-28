package com.desha.Beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Viktor on 2/26/2017.
 */

@Entity
@IdClass(Menu_Item_Type.class)
public class Menu_Item_Type implements Serializable {

    @Id
    private String name;

    @Column
    private String description;

    public Menu_Item_Type(String name, String desctiption) {
        this.name = name;
        this.description = desctiption;
    }

    public Menu_Item_Type()
    {}

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
}
