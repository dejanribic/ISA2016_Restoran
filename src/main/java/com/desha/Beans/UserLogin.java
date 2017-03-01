package com.desha.Beans;

public class UserLogin {

    private String email;

    private String password;

    private String name;

    private int type;

    private String etype;

    private String restname;

    private boolean firstlog;

    public UserLogin ()
    {
        this.etype = "nista";
        this.restname = "nema";
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEtype() {
        return etype;
    }

    public void setEtype(String etype) {
        this.etype = etype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestname() {
        return restname;
    }

    public void setRestname(String restname) {
        this.restname = restname;
    }

    public boolean isFirstlog() {
        return firstlog;
    }

    public void setFirstlog(boolean firstlog) {
        this.firstlog = firstlog;
    }
}
