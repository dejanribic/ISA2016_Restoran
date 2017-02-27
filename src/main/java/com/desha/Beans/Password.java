package com.desha.Beans;

/**
 * Created by Nenad on 2/27/2017.
 */
public class Password {
    private String old;
    private String newr;

    public Password() {
    }

    public Password(String old, String newr) {
        this.old = old;
        this.newr = newr;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getNewr() {
        return newr;
    }

    public void setNewr(String newr) {
        this.newr = newr;
    }
}
