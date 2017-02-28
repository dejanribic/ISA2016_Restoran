package com.desha.Repositories;

import com.desha.Beans.Order;

import com.desha.Beans.OrderKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Viktor on 2/27/2017.
 */
public interface OrderRepository extends JpaRepository<Order, OrderKey> {

    List<Order> findByResname(String resname);


}
