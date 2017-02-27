package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Date;

/**
 * Created by Nenad on 2/23/2017.
 */
@Entity
@IdClass(OfferKey.class)
public class Offer {
    @Id
    @Column(name = "demand_num")
    private int demandNum;
    @Id
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Id
    @Column(name = "supplier_email")
    private String supplierEmail;
    @Column(name = "dilivered_until")
    private Date diliveredUntil;

    private boolean accepted;
    private int price;

    public Offer() {
    }

    public Offer(int demandNum, String restaurantName, String supplierEmail, Date diliveredUntil, int price, boolean accepted) {
        this.demandNum = demandNum;
        this.restaurantName = restaurantName;
        this.supplierEmail = supplierEmail;
        this.diliveredUntil = diliveredUntil;
        this.price = price;
        this.accepted = accepted;
    }

    public int getDemandNum() {
        return demandNum;
    }

    public void setDemandNum(int demandNum) {
        this.demandNum = demandNum;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public Date getDiliveredUntil() {
        return diliveredUntil;
    }

    public void setDiliveredUntil(Date diliveredUntil) {
        this.diliveredUntil = diliveredUntil;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
