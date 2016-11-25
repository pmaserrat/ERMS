package com.team19.controller.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.team19.controller.model.Incident;

@Repository
public class IncidentRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static String INCIDENT = " Incident ";

	public List<Incident> getAllIncidents() {

		List<Incident> incidents = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.SELECT);
		builder.append("*");
		builder.append(SQLUtils.FROM);
		builder.append(INCIDENT);

		String sql = builder.toString();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			Incident incident = new Incident();
			incident.setID((Integer) row.get("ID"));
			incident.setDate((Timestamp) row.get("Date"));
			incident.setLatitude((BigDecimal) row.get("Latitude"));
			incident.setLongitude((BigDecimal) row.get("Longitude"));
			incident.setDescription((String) row.get("Description"));
			incidents.add(incident);
		}

		return incidents;

	}
	
	public List<Incident> getAllIncidents(String userName) {

		List<Incident> incidents = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.SELECT);
		builder.append("*");
		builder.append(SQLUtils.FROM);
		builder.append(INCIDENT);
		builder.append(SQLUtils.WHERE);
		builder.append("Username = '%s'");

		String sql = String.format(builder.toString(), userName);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			Incident incident = new Incident();
			incident.setID((Integer) row.get("ID"));
			incident.setDate((Timestamp) row.get("Date"));
			incident.setLatitude((BigDecimal) row.get("Latitude"));
			incident.setLongitude((BigDecimal) row.get("Longitude"));
			incident.setDescription((String) row.get("Description"));
			incidents.add(incident);
		}

		return incidents;

	}

	public void createIncident(Incident incident) {
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.INSERT_INTO);
		builder.append(INCIDENT);
		builder.append("(Username,Date, Description, Latitude,Longitude)");
		builder.append(SQLUtils.VALUES + "(?, ?, ?, ?, ?)");
		String sql = builder.toString();
		
		
		jdbcTemplate.update(sql, new Object[] { incident.getUsername(), incident.getDate(), incident.getDescription(), incident.getLatitude(), incident.getLongitude() });
		
	}

}
