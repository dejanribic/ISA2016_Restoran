package com.desha.Repositories;


import com.desha.Beans.Region;
import com.desha.Beans.RegionKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

/**
 * Created by Nenad on 3/1/2017.
 */
public interface RegionRepository extends JpaRepository<Region, RegionKey>{
    ArrayList<Region> findByResname(String resname);
}
