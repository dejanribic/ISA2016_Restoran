package com.desha.Controllers;

import com.desha.Beans.Demand;
import com.desha.Beans.UserLogin;
import com.desha.Repositories.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * Created by Nenad on 2/24/2017.
 */
@RestController
@RequestMapping(value = "/demand")
public class DemandController {
    private DemandRepository repository;

    @Autowired
    public DemandController(DemandRepository repository){
        this.repository = repository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Demand> getAll() { return repository.findByRestaurantName("r"); }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public Demand create(@RequestBody Demand newDemand) {
        newDemand.setRestaurantName("r");
        return repository.save(newDemand);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public void remove(@RequestBody Demand toRemove) {
        repository.delete(toRemove);
    }
}
