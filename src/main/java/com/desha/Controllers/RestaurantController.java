package com.desha.Controllers;

import com.desha.Beans.Restaurant;
import com.desha.Beans.Restaurant_Rating;
import com.desha.Repositories.RestaurantRepository;
import com.desha.Repositories.Restaurant_RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private RestaurantRepository restaurantRepository;
    private Restaurant_RatingRepository restaurant_ratingRepository;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository, Restaurant_RatingRepository restaurant_ratingRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurant_ratingRepository = restaurant_ratingRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @RequestMapping(value = "/getByName/{name}")
    public Restaurant getByName(@PathVariable String name) {
        Restaurant ret = restaurantRepository.findByName(name);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.setAttribute("activeRestaurant", ret, ServletRequestAttributes.SCOPE_SESSION);
        return ret;
    }

    @RequestMapping(value = "/getActive", method = RequestMethod.GET)
    public Restaurant getActive() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant ret = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        return ret;
    }

    @RequestMapping(value = "/getRating", method = RequestMethod.GET)
    public double getRating() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        List<Restaurant_Rating> ratings = restaurant_ratingRepository.findByRestaurantName(res.getName());
        ArrayList<Restaurant_Rating> rts = new ArrayList<>(ratings);
        double rating = 0;
        for (Restaurant_Rating rtg : rts) {
            rating += rtg.getRating();

        }
        if (rts.size() != 0) {
            rating /= rts.size();
        }
        return rating;
    }


    @RequestMapping(value = "/exists", method = RequestMethod.PUT)
    public Restaurant exists(@RequestBody Restaurant sentRestaurant) {
        Restaurant restaurant = restaurantRepository.findByName(sentRestaurant.getName());
        return restaurant;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody Restaurant newRestaurant) {
        Restaurant r = restaurantRepository.save(newRestaurant);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.setAttribute("activeRestaurant", r, ServletRequestAttributes.SCOPE_SESSION);
    }


}
