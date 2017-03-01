package com.desha.Controllers;

import com.desha.Beans.Manager;
import com.desha.Beans.Restaurant_Manager;
import com.desha.Beans.UserLogin;
import com.desha.Repositories.ManagerRepository;
import com.desha.Repositories.Restaurant_ManagerRepository;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.List;

/**
 * Created by Nenad on 2/25/2017.
 */
@RestController
@RequestMapping(value = "/managers")
public class RestaurantManagerController {
    private ManagerRepository managerRepository;
    private Restaurant_ManagerRepository restaurantManagerRepository;

    @Value("${sendGridAPIKey}")
    private String sendGridAPIKey;

    @Autowired
    public RestaurantManagerController(ManagerRepository managerRepository, Restaurant_ManagerRepository restaurantManagerRepository) {
        this.managerRepository = managerRepository;
        this.restaurantManagerRepository = restaurantManagerRepository;
    }

    @RequestMapping(value = "/all/{name}", method = RequestMethod.GET)
    public List<Restaurant_Manager> getByRestaurantName(@PathVariable String name) {
        return restaurantManagerRepository.findByRestaurantName(name);
    }

    @RequestMapping(value = "/allByMail", method = RequestMethod.GET)
    public List<Restaurant_Manager> getByManagerEmail() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        UserLogin user = (UserLogin) attr.getAttribute("user", ServletRequestAttributes.SCOPE_SESSION);
        if (user.getType() == 2)
            return restaurantManagerRepository.findByManagerEmail(user.getEmail());
        return null;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public Restaurant_Manager removeRestaurantManager(@RequestBody Restaurant_Manager toRemove) {

        restaurantManagerRepository.delete(toRemove);
        return toRemove;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public Restaurant_Manager create(@RequestBody Restaurant_Manager newManager) {
        Manager nm = new Manager(newManager.getManagerEmail(), "1");

        if (managerRepository.findByEmail(newManager.getManagerEmail()) == null) {
            managerRepository.save(nm);
        }
        restaurantManagerRepository.save(newManager);
        managerRepository.save(nm);
        return newManager;
    }

}
