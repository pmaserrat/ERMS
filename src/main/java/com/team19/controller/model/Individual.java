package com.team19.controller.model;

import java.sql.Timestamp;

/**
 * Created by akeem on 11/2/16.
 */
public class Individual extends  User {

    private String jobTitle;
    private Timestamp dateHired;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Timestamp getDateHired() {
        return dateHired;
    }

    public void setDateHired(Timestamp dateHired) {
        this.dateHired = dateHired;
    }
}
