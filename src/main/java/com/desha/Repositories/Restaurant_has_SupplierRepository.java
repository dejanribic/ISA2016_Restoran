package com.desha.Repositories;

import com.desha.Beans.Restaurant_has_Supplier;
import com.desha.Beans.Restaurant_has_SupplierKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nenad on 2/26/2017.
 */
public interface Restaurant_has_SupplierRepository  extends JpaRepository<Restaurant_has_Supplier, Restaurant_has_SupplierKey>{
    List<Restaurant_has_Supplier> findBySupplierEmail(String supplierEmail);
    List<Restaurant_has_Supplier> findByRestaurantName(String restaurantName);
}
