package com.desha.Repositories;

import com.desha.Beans.Work_Schedule;
import com.desha.Beans.Work_ScheduleKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Viktor on 2/28/2017.
 */
public interface Work_SheduleRepository extends JpaRepository<Work_Schedule, Work_ScheduleKey> {

    List<Work_Schedule> findByEmailAndResname(String email , String resname );


}