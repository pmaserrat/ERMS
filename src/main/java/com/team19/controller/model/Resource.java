package com.team19.controller.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

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
    private String PrimaryESF;
    private List<ESF> additonalESF;
    private List<String> capabilities;
    private double distance;
    
    public final static String READY =  "Ready";
    public final static String DEPLOYED = "Deployed";
    public final static String IN_REPAIR = "In Repair";
    public final static String IN_USE = "In Use";
   

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

	public String getPrimaryESF() {
		return PrimaryESF;
	}

	public void setPrimaryESF(String primaryESF) {
		PrimaryESF = primaryESF;
	}

	public List<ESF> getAdditonalESF() {
		return additonalESF;
	}

	public void setAdditonalESF(List<ESF> additonalESF) {
		this.additonalESF = additonalESF;
	}

	public List<String> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<String> capabilities) {
		this.capabilities = capabilities;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(Double dist) {
		this.distance = dist;
	}

	
	@Override
	public String toString() {
		return "Resource [username=" + username + ", ID=" + ID + ", name=" + name + ", nextAvailableDate="
				+ nextAvailableDate + ", status=" + status + ", model=" + model + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", amount=" + amount + ", costTimeUnit=" + costTimeUnit + ", PrimaryESF="
				+ PrimaryESF + ", additonalESF=" + additonalESF + ", capabilities=" + capabilities + "]";
	}
	
	
    
    
}
