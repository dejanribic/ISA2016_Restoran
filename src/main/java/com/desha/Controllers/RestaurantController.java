package com.desha.Controllers;

import com.desha.Beans.Restaurant;
import com.desha.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private RestaurantRepository repository;

    @Autowired
    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Restaurant> getAll() { return repository.findAll(); }

    @RequestMapping(value = "/getByName/{name}")
    public Restaurant getByName(@PathVariable String name) {
        Restaurant ret = repository.findByName(name);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.setAttribute("activeRestaurant", ret, ServletRequestAttributes.SCOPE_SESSION);
        return ret;
    }


    @RequestMapping(value = "/exists", method = RequestMethod.PUT)
    public Restaurant exists(@RequestBody Restaurant sentRestaurant) {
        Restaurant restaurant = repository.findByName(sentRestaurant.getName());
        return restaurant;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody Restaurant newRestaurant) {
        repository.save(newRestaurant);
    }


}
