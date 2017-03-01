package com.desha.Beans;

/**
 * Created by Nenad on 2/28/2017.
 */
public class MenuItemWithRating {
    private Menu_Item menuItem;
    private double rating;

    public MenuItemWithRating() {
    }

    public MenuItemWithRating(Menu_Item menuItem, double rating) {
        this.menuItem = menuItem;
        this.rating = rating;
    }

    public Menu_Item getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(Menu_Item menuItem) {
        this.menuItem = menuItem;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
