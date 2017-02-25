package com.desha.Controllers;

import com.desha.Beans.Manager;
import com.desha.Beans.Restaurant_Manager;
import com.desha.Repositories.ManagerRepository;
import com.desha.Repositories.Restaurant_ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nenad on 2/25/2017.
 */
@RestController
@RequestMapping(value = "/managers")
public class RestaurantManagerController {
    private ManagerRepository managerRepository;
    private Restaurant_ManagerRepository restaurantManagerRepository;

    @Autowired
    public RestaurantManagerController(ManagerRepository managerRepository, Restaurant_ManagerRepository restaurantManagerRepository) {
        this.managerRepository = managerRepository;
        this.restaurantManagerRepository = restaurantManagerRepository;
    }

    @RequestMapping(value = "/all/{name}", method = RequestMethod.GET)
    public List<Restaurant_Manager> getByRestaurantName(@PathVariable String name) {
        return restaurantManagerRepository.findByRestaurantName(name);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public Restaurant_Manager removeRestaurantManager(@RequestBody Restaurant_Manager toRemove) {

        restaurantManagerRepository.delete(toRemove);
        return toRemove;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public Restaurant_Manager create(@RequestBody Restaurant_Manager newManager) {
        Manager nm = new Manager(newManager.getManagerEmail(),"");

        if(managerRepository.findByEmail(newManager.getManagerEmail())== null) {
            managerRepository.save(nm);
            // TODO send mail
        }
        restaurantManagerRepository.save(newManager);
        managerRepository.save(nm);
        return newManager;
    }
}
