package com.desha.Repositories;

import com.desha.Beans.Seating_Table;
import com.desha.Beans.Seating_TableKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nenad on 3/1/2017.
 */
public interface SeatingTableRepository extends JpaRepository<Seating_Table,Seating_TableKey> {

}
