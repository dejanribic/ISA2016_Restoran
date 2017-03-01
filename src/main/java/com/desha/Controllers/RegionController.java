package com.desha.Controllers;

import com.desha.Beans.Region;
import com.desha.Beans.Restaurant;
import com.desha.Beans.Seating_Table;
import com.desha.Repositories.RegionRepository;
import com.desha.Repositories.SeatingTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nenad on 3/1/2017.
 */
@RestController
@RequestMapping(value = "/regionz")
public class RegionController {

    private RegionRepository regionRepository;
    private SeatingTableRepository seatingTableRepository;

    @Autowired
    public RegionController(RegionRepository regionRepository, SeatingTableRepository seatingTableRepository) {
        this.regionRepository = regionRepository;
        this.seatingTableRepository = seatingTableRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Region> getAll() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        return regionRepository.findByResname(res.getName());
    }
    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody Region toAdd ) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Restaurant res = (Restaurant) attr.getAttribute("activeRestaurant", ServletRequestAttributes.SCOPE_SESSION);
        toAdd.setResname(res.getName());
        regionRepository.save(toAdd);
    }
}
