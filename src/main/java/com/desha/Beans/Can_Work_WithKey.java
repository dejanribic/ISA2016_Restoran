package com.desha.Beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.io.StreamCorruptedException;

/**
 * Created by Viktor on 2/25/2017.
 */
@Embeddable
public class Can_Work_WithKey implements Serializable
{
    @Id
    private String menu_item_type_name ;
    @Id
    @Column(name="employee_email")
    private String email;

    public Can_Work_WithKey(){}
    public Can_Work_WithKey( String empolyee_email , String menu_item_type_name)
    {
        this.menu_item_type_name = menu_item_type_name;
        this.email = empolyee_email;
    }

    public String getMenu_item_type_name() {
        return menu_item_type_name;
    }

    public void setMenu_item_type_name(String menu_item_type_name) {
        this.menu_item_type_name = menu_item_type_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
