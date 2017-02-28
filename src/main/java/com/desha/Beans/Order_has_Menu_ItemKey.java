package com.desha.Beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Viktor on 2/26/2017.
 */

@Embeddable
public class Order_has_Menu_ItemKey implements Serializable {


    @Id
    @Column(name="order_num")
    private int num;

    @Id
    @Column(name="reservation_id")
    private int id;

    @Id
    @Column(name="restaurant_name")
    private String restname;

    @Id
    @Column(name="guest_email")
    private String email;

    @Id
    @Column(name="menu_item_name")
    private String menuitemname;




    public Order_has_Menu_ItemKey(int order_num, int reservation_id, String restaurant_name, String guest_email, String menu_item_name) {
        this.num = order_num;
        this.id = reservation_id;
        this.restname = restaurant_name;
        this.email = guest_email;
        this.menuitemname = menu_item_name;

    }

    public Order_has_Menu_ItemKey() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestname() {
        return restname;
    }

    public void setRestname(String restname) {
        this.restname = restname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMenuitemname() {
        return menuitemname;
    }

    public void setMenuitemname(String menuitemname) {
        this.menuitemname = menuitemname;
    }


}