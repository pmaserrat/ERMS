package com.team19.controller.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class SearchedResource {
	
	private String username;
    private Integer resourceID;
    private String name;
    private Timestamp nextAvailableDate;
    private String status;
    private String model;
    private BigDecimal rlatitude;
    private BigDecimal rlongitude;
    private BigDecimal ilatitude;
    private BigDecimal ilongitude;
    private double amount;
    private String costTimeUnit;
    private String PrimaryESF;
    private List<ESF> additonalESF;
    private List<String> capabilities;
    private Integer incidentID;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getResourceID() {
		return resourceID;
	}
	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
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
	public BigDecimal getRLatitude() {
		return rlatitude;
	}
	public void setRLatitude(BigDecimal latitude) {
		this.rlatitude = latitude;
	}
	public BigDecimal getRLongitude() {
		return rlongitude;
	}
	public void setRLongitude(BigDecimal longitude) {
		this.rlongitude = longitude;
	}
	public BigDecimal getILatitude() {
		return ilatitude;
	}
	public void setILatitude(BigDecimal latitude) {
		this.ilatitude = latitude;
	}
	public BigDecimal getILongitude() {
		return ilongitude;
	}
	public void setILongitude(BigDecimal longitude) {
		this.ilongitude = longitude;
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
	public Integer getIncidentID() {
		return incidentID;
	}
	public void setIncidentID(Integer incidentID) {
		this.incidentID = incidentID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String description;
	
}
