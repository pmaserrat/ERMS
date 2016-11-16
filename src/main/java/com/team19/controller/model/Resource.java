package com.team19.controller.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by akeem on 11/5/16.
 */
public class Resource {

    private String username;
    private Integer ID;
    private String name;
    private Timestamp nextAvailableDate;
    private String status;
    private String model;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private double amount;
    private String costTimeUnit;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getNextAvailableDate() {
        return nextAvailableDate;
    }

    public void setNextAvailableDate(Timestamp nextAvailableDate) {
        this.nextAvailableDate = nextAvailableDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCostTimeUnit() {
        return costTimeUnit;
    }

    public void setCostTimeUnit(String costTimeUnit) {
        this.costTimeUnit = costTimeUnit;
    }
}