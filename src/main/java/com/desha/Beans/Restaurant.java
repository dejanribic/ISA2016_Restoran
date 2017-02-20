package com.desha.Beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Blob;

@Entity
public class Restaurant {

    @Id
    private String name;
    @Column
    private String description;

    @Column
    private String coordinates;

    @Column
    private Integer tablegrid_width;

    @Column
    private Integer tablegrid_height;

    @JsonIgnore
    @Column
    private Blob image;

    public Restaurant(){}

    public Restaurant(String name, String description, String coordinates, Integer tablegrid_width, Integer tablegrid_height, Blob image) {
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
        this.tablegrid_width = tablegrid_width;
        this.tablegrid_height = tablegrid_height;
        this.image = image;
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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getTablegrid_width() {
        return tablegrid_width;
    }

    public void setTablegrid_width(Integer tablegrid_width) {
        this.tablegrid_width = tablegrid_width;
    }

    public Integer getTablegrid_height() {
        return tablegrid_height;
    }

    public void setTablegrid_height(Integer tablegrid_height) {
        this.tablegrid_height = tablegrid_height;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }



}
