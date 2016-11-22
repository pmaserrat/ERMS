package com.team19.controller.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.team19.controller.model.Requests;

import utils.SQLUtils;


@Repository
public class RequestsResposiotry {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String REQUESTS = "Requests";
	
	public List<Requests> getAllRequests(String userName) {
		
		List<Requests> requests = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.SELECT);
		builder.append("*");
		builder.append(SQLUtils.FROM);
		builder.append(REQUESTS);
		builder.append(SQLUtils.WHERE);
		builder.append("Submitter = '%s'");
		builder.append(SQLUtils.OR);
		builder.append("ResourceOwner = '%s'");

		String sql = String.format(builder.toString(), userName, userName);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) {
			Requests request = new Requests();
			request.setIncidentID((Integer) row.get("IncidentID"));
			request.setResourceID((Integer) row.get("ResourceID"));
			request.setRequestDate((Timestamp) row.get("RequestDate"));
			request.setReturnDate((Timestamp) row.get("ReturnDate"));
			request.setResourceOwner((String) row.get("ResourceOwner"));
			request.setSubmitter((String) row.get("Submitter"));
			request.setStatus((String) row.get("Status"));
			requests.add(request);
		}
		
		
		return requests;
		
	}

}
