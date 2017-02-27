package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Nenad on 2/26/2017.
 */
@Embeddable
public class Restaurant_has_SupplierKey implements Serializable {
    @Id
    @Column(name = "restaurant_name")
    private String restaurantName;

    @Id
    @Column(name= "supplier_email")
    private String supplierEmail;

    public Restaurant_has_SupplierKey() {
    }

    public Restaurant_has_SupplierKey(String restaurantName, String supplierEmail) {
        this.restaurantName = restaurantName;
        this.supplierEmail = supplierEmail;
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
