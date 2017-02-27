package com.desha.Controllers;

import com.desha.Beans.Demand;
import com.desha.Beans.Restaurant;
import com.desha.Beans.Restaurant_has_Supplier;
import com.desha.Beans.UserLogin;
import com.desha.Repositories.DemandRepository;
import com.desha.Repositories.Restaurant_has_SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nenad on 2/24/2017.
 */
@RestController
@RequestMapping(value = "/demand")
public class DemandController {
    private DemandRepository demandRepository;
    private Restaurant_has_SupplierRepository rhsRepository;

    @Autowired
    public DemandController(DemandRepository demandRepository,Restaurant_has_SupplierRepository rhsRepository ) {
        this.demandRepository = demandRepository;
        this.rhsRepository = rhsRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Demand> getAll() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        return demandRepository.findByRestaurantNameAndEndGreaterThan(res.getName(), new Date((new java.util.Date()).getTime()));
    }
    @RequestMapping(value = "/allForSupplier", method = RequestMethod.GET)
    public List<Demand> getAllForSupplier() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        UserLogin res = (UserLogin) attr.getAttribute("user", ServletRequestAttributes.SCOPE_SESSION);
        if(res.getType() == 3) {
            List<Restaurant_has_Supplier> rhs = rhsRepository.findBySupplierEmail(res.getEmail());
            ArrayList<Restaurant_has_Supplier> rhsar = new ArrayList<>(rhs);
            ArrayList<String> restaurantNames = new ArrayList<>();
            for (Restaurant_has_Supplier r : rhsar) {
                restaurantNames.add(r.getRestaurantName());
            }
            return demandRepository.findByRestaurantNameInAndEndGreaterThan(restaurantNames, new Date((new java.util.Date()).getTime()));
        }
        return null;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public Demand create(@RequestBody Demand newDemand) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        newDemand.setRestaurantName(res.getName());
        newDemand.setStart(new Date((new java.util.Date()).getTime()));
        return demandRepository.save(newDemand);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public void remove(@RequestBody Demand toRemove) {
        demandRepository.delete(toRemove);
    }
}
