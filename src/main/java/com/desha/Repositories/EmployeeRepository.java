package com.desha.Repositories;

/**
 * Created by Viktor on 2/25/2017.
 */
import com.desha.Beans.Employee;
import com.desha.Beans.EmployeeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.desha.Beans.Reservation;

import java.util.ArrayList;

public interface EmployeeRepository extends JpaRepository<Employee, EmployeeKey> {

    Employee findByEmailAndPassword(String email, String password);

    Employee findByEmail(String email);


    Employee findByEmailAndRestaurantName(String email, String resname);
    ArrayList<Employee> findByRestaurantName(String restaurantName);

}
