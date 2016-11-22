package com.team19.controller.model;

import java.sql.Timestamp;

/**
 * Created by akeem on 11/5/16.
 */
public class Deployed {
    private Integer incidentId;
    private Integer resourceID;
    private Timestamp startdate;

    public Integer getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Integer incidentId) {
        this.incidentId = incidentId;
    }

    public Integer getResourceID() {
        return resourceID;
    }

    public void setResourceID(Integer resourceID) {
        this.resourceID = resourceID;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }
}
