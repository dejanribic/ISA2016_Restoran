package com.desha.Controllers;

import com.desha.Beans.Restaurant;
import com.desha.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private RestaurantRepository repository;

    @Autowired
    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/all")
    public List<Restaurant> getAll() {
        return repository.findAll();
    }


}
