package com.team19.controller.model;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by akeem on 11/5/16.
 */
public class Schedules_Repair {
    private String username;
    private Integer resourceId;
    private Timestamp repairStartDate;
    private Integer daysInRepair;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Timestamp getRepairStartDate() {
        return repairStartDate;
    }

    public void setRepairStartDate(Timestamp repairStartDate) {
        this.repairStartDate = repairStartDate;
    }

    public Integer getDaysInRepair() {
        return daysInRepair;
    }

    public void setDaysInRepair(Integer daysInRepair) {
        this.daysInRepair = daysInRepair;
    }
}
