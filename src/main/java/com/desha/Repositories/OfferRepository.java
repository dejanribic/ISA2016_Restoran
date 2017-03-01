package com.desha.Repositories;

import com.desha.Beans.Offer;
import com.desha.Beans.OfferKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nenad on 2/24/2017.
 */
public interface OfferRepository extends JpaRepository<Offer, OfferKey> {
    ArrayList<Offer> findByDemandNumAndRestaurantName(int demandNum, String restaurantName);
    List<Offer> findBySupplierEmail(String supplierEmail);
    Offer findByDemandNumAndRestaurantNameAndSupplierEmail(int demandNum,String restaurantName,String supplierEmail);
 }
