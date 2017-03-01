package com.desha.Repositories;

import com.desha.Beans.Employee_Rating;
import com.desha.Beans.Employee_RatingKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Viktor on 3/1/2017.
 */
public interface Employee_RatingRepository extends JpaRepository<Employee_Rating, Employee_RatingKey> {



        }