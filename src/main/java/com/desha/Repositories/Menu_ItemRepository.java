package com.desha.Repositories;

import com.desha.Beans.Menu_Item;
import com.desha.Beans.Menu_ItemKey;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Nenad on 2/22/2017.
 */
public interface Menu_ItemRepository extends JpaRepository<Menu_Item, Menu_ItemKey> {
    Menu_Item findByName(String name);

}
