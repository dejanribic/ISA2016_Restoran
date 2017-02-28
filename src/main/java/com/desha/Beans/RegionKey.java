package com.desha.Beans;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Viktor on 2/28/2017.
 */
@Embeddable
public class RegionKey implements Serializable {

    @Id
    private int id;

    @Id
    @Column(name = "restaurant_name")
    private String resname;

    public RegionKey(int id, String resname) {
        this.id = id;

        this.resname = resname;
    }

    public RegionKey() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

      public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }
}