package com.desha.Repositories;

import com.desha.Beans.Employee_Rating;
import com.desha.Beans.Employee_RatingKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nenad on 3/1/2017.
 */
public interface EmployeeRatingRepository extends JpaRepository<Employee_Rating,Employee_RatingKey>{
    ArrayList<Employee_Rating> findByRestaurantNameAndEmployeeEmail(String restaurantName, String employeeEmail);
}
