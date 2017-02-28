package com.desha.Beans;

/**
 * Created by Nenad on 2/28/2017.
 */
public class MenuItemWithRating {
    private Menu_Item menuItem;
    private int rating;

    public MenuItemWithRating() {
    }

    public MenuItemWithRating(Menu_Item menuItem, int rating) {
        this.menuItem = menuItem;
        this.rating = rating;
    }

    public Menu_Item getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(Menu_Item menuItem) {
        this.menuItem = menuItem;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
