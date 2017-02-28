package com.desha.Beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Viktor on 2/25/2017.
 */

@Entity
@IdClass(Can_Work_WithKey.class)
public class Can_Work_With implements Serializable
{

    @Id
    @Column(name="employee_email")
    private String email;
    @Id
    private String menu_item_type_name ;

    public Can_Work_With(){}

    public Can_Work_With( String Email , String Mik )
    {
        this.menu_item_type_name = Mik;
        this.email = Email;
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMenu_item_type_name() {
        return menu_item_type_name;
    }

    public void setMenu_item_type_name(String menu_item_type_name) {
        this.menu_item_type_name = menu_item_type_name;
    }
}
