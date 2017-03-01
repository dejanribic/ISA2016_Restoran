package com.desha.Beans;

/**
 * Created by Nenad on 2/28/2017.
 */
public class EmployeeWithEarningsAndRating {
    private Employee employee;
    private double rating;
    private int earnings;
    private boolean isWaiter;

    public EmployeeWithEarningsAndRating() {
    }

    public EmployeeWithEarningsAndRating(Employee employee, double rating, int earnings, boolean isWaiter) {
        this.employee = employee;
        this.rating = rating;
        this.earnings = earnings;
        this.isWaiter = isWaiter;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getEarnings() {
        return earnings;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }

    public boolean isWaiter() {
        return isWaiter;
    }

    public void setWaiter(boolean waiter) {
        isWaiter = waiter;
    }
}
