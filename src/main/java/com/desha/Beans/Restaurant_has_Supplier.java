package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by Nenad on 2/26/2017.
 */
@Entity
@IdClass(Restaurant_has_SupplierKey.class)
public class Restaurant_has_Supplier {
    @Id
    @Column(name = "restaurant_name")
    private String restaurantName;

    @Id
    @Column(name= "supplier_email")
    private String supplierEmail;

    public Restaurant_has_Supplier() {
    }

    public Restaurant_has_Supplier(String restaurantName, String supplierEmail) {
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
