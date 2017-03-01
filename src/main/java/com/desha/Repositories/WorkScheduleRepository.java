package com.desha.Repositories;

import com.desha.Beans.Work_Schedule;
import com.desha.Beans.Work_ScheduleKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

/**
 * Created by Nenad on 3/1/2017.
 */
public interface WorkScheduleRepository extends JpaRepository<Work_Schedule, Work_ScheduleKey> {
    ArrayList<Work_Schedule> findByEmailAndResname(String email, String resname);

    ArrayList<Work_Schedule> findByResname(String resname);

}
