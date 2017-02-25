package com.desha.Repositories;

import com.desha.Beans.Restaurant_Manager;
import com.desha.Beans.Restaurant_ManagerKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nenad on 2/25/2017.
 */
public interface Restaurant_ManagerRepository extends JpaRepository<Restaurant_Manager, Restaurant_ManagerKey>{
    List<Restaurant_Manager> findByManagerEmail(String managerEmail);
    List<Restaurant_Manager> findByRestaurantName(String managerEmail);
}
