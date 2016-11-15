package com.team19.controller.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Incident {

	private String username;
	private Integer ID;
	private String description;
	private Timestamp date;
	private BigDecimal latitude;
	private BigDecimal longitude;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
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

}
