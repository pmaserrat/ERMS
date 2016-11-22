package com.team19.controller.model;

/**
 * Created by akeem on 11/2/16.
 */
public class Company extends  User {

    private String hQLocation;

    public String gethQLocation() {
        return hQLocation;
    }

    public void sethQLocation(String hQLocation) {
        this.hQLocation = hQLocation;
    }
}
