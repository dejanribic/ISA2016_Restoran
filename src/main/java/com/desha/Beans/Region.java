package com.desha.Beans;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Viktor on 2/28/2017.
 */
@Entity
@IdClass(RegionKey.class)
public class Region implements Serializable {

    @Id
    private int id;

    @Column
    private String description;

    @Column
    private int x;

    @Column
    private int width;

    @Column
    private int y;

    @Column
    private int height;

    @Id
    @Column(name = "restaurant_name")
    private String resname;

    public Region(int id, String description, int x, int width, int y, int height, String resname) {
        this.id = id;
        this.description = description;
        this.x = x;
        this.width = width;
        this.y = y;
        this.height = height;
        this.resname = resname;
    }

    public Region() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }
}