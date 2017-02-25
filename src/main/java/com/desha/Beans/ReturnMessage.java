package com.desha.Beans;

/**
 * Created by Nenad on 2/25/2017.
 */
public class ReturnMessage {
    private String message;

    public ReturnMessage(String message) {
        this.message = message;
    }

    public ReturnMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
