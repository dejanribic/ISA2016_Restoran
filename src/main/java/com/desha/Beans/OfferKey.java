package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Nenad on 2/24/2017.
 */
@Embeddable
public class OfferKey implements Serializable {
    @Id
    @Column(name = "demand_num")
    private int demandNum;
    @Id
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Id
    @Column(name = "supplier_email")
    private String supplierEmail;

    public OfferKey() {
    }

    public OfferKey(int demandNum, String restaurantName, String supplierEmail) {
        this.demandNum = demandNum;
        this.restaurantName = restaurantName;
        this.supplierEmail = supplierEmail;
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
}
