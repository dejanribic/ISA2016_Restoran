package com.desha.Repositories;

import com.desha.Beans.Can_Work_With;
import com.desha.Beans.Order_has_Menu_Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Viktor on 2/26/2017.
 */
public interface Order_has_Menu_ItemRepository extends JpaRepository<Order_has_Menu_Item, String> {

    List<Order_has_Menu_Item> findByRestname(String restname);
    List<Order_has_Menu_Item>  findByNumAndIdAndRestnameAndEmail(int num , int id , String restname , String email );
    Order_has_Menu_Item findByNumAndIdAndRestnameAndEmailAndMenuitemname(int num , int id , String restname , String email , String menuitemname);

    List<Order_has_Menu_Item> findByIdAndRestnameAndEmailAndMenuitemname(int resid, String restname, String gmail, String menuitemname);
    ArrayList<Order_has_Menu_Item> findByRestnameAndIdIn(String restaurantName, ArrayList<Integer> id);
}
