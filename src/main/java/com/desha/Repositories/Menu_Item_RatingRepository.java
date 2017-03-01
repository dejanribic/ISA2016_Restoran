package com.desha.Repositories;

import com.desha.Beans.Menu_ItemKey;
import com.desha.Beans.Menu_Item_Rating;
import com.desha.Beans.Menu_Item_RatingKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nenad on 2/28/2017.
 */
public interface Menu_Item_RatingRepository extends JpaRepository<Menu_Item_Rating, Menu_Item_RatingKey> {

    List<Menu_Item_Rating> findByMenuItemName(String name);


}
