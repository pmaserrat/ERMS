package com.team19.controller.model;


import java.sql.Timestamp;

/**
 * Created by akeem on 11/5/16.
 */
public class Requests {

    private Integer incidentID;
    private Integer resourceID;
    private Timestamp requestDate;
    private Timestamp returnDate;
    private String resourceOwner;
    private String submitter;
    private String Status;


    public Integer getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(Integer incidentID) {
        this.incidentID = incidentID;
    }

    public Integer getResourceID() {
        return resourceID;
    }

    public void setResourceID(Integer resourceID) {
        this.resourceID = resourceID;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public String getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(String resourceOwner) {
        this.resourceOwner = resourceOwner;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
