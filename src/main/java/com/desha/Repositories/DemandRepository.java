package com.desha.Repositories;

import com.desha.Beans.Demand;
import com.desha.Beans.DemandKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

/**
 * Created by Nenad on 2/24/2017.
 */
public interface DemandRepository extends JpaRepository<Demand, DemandKey> {

    List<Demand> findByRestaurantNameAndEndGreaterThan(String restaurantName,Date end);
    List<Demand> findByRestaurantNameInAndEndGreaterThan(List<String> restaurantName,Date end);

}
