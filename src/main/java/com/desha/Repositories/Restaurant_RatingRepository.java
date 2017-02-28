package com.desha.Repositories;

import com.desha.Beans.Restaurant_Rating;
import com.desha.Beans.Restaurant_RatingKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nenad on 2/28/2017.
 */
public interface Restaurant_RatingRepository extends JpaRepository<Restaurant_Rating,Restaurant_RatingKey> {
    List<Restaurant_Rating> findByRestaurantName(String restaurantName);
}
