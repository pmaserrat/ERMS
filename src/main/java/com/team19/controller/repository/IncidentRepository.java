package com.team19.controller.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.team19.controller.model.Incident;
import utils.SQLUtils;

@Repository
public class IncidentRepository {
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	 	
	 	public static String INCIDENT = "Incident";

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

	        for ( Map<String, Object> row: rows) {
	            Incident incident = new Incident();
	            incident.setID((Integer) row.get("ID"));
	            incident.setDescription((String) row.get("Description"));
	            incidents.add(incident);
	        }

	        return incidents;

	    }

}
