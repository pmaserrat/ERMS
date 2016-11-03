package com.team19.controller.model;

import java.sql.Timestamp;

/**
 * Created by akeem on 11/2/16.
 */
public  abstract class User {
    private String userName;
    private String Name;
    private String password;




    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
