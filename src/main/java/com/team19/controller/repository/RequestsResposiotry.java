package com.team19.controller.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.team19.controller.model.Requests;


@Repository
public class RequestsResposiotry {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	private static final String REQUESTS = "Requests";
	public static final String PENDING = "PENDING";
	public static final String ACCEPTED = "ACCEPTED";
	public static final String REJECTED = "REJECTED";
	
	public List<Requests> getAllRequests(String userName) {
		
		List<Requests> requests = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.SELECT);
		builder.append("Requests.IncidentID, Requests.ResourceID, Requests.RequestDate, Requests.ReturnDate, Requests.ResourceOwner, Requests.Submitter, Requests.Status, Resource.name, Incident.Description, Resource.Status AS RStatus");
		builder.append(SQLUtils.FROM);
		builder.append(REQUESTS);
		builder.append(SQLUtils.JOIN + "Resource");
		builder.append(SQLUtils.ON + "Requests.ResourceID = Resource.ID");
		builder.append(SQLUtils.JOIN + "Incident");
		builder.append(SQLUtils.ON + "Incident.ID = Requests.IncidentID");
		builder.append(SQLUtils.WHERE);
		builder.append("Submitter = '%s'");
		builder.append(SQLUtils.OR);
		builder.append("ResourceOwner = '%s'");
		
		String sql = String.format(builder.toString(), userName, userName);
		System.out.println(sql);
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
			request.setResourceName((String) row.get("name"));
			request.setIncidentDescription((String) row.get("Description"));
			request.setResourceStatus(((String) row.get("RStatus")));
			requests.add(request);
		}
		
		System.out.println(requests.size());
		return requests;
		
	}
	
	public void save(Requests request) {
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.INSERT_INTO);
		builder.append(REQUESTS);
		builder.append("(IncidentID,ResourceID, RequestDate, ReturnDate,ResourceOwner, Submitter, Status)");
		builder.append(SQLUtils.VALUES + "(?, ?, ?, ?, ?, ?, ?)");
		String sql = builder.toString();
		
		jdbcTemplate.update(sql, new Object[] { request.getIncidentID(), request.getResourceID(), request.getRequestDate(), request.getReturnDate(), request.getResourceOwner(), request.getSubmitter(), PENDING });
	}
	
	public void delete(Integer resourceId, Integer incidentId) {
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.DELETE);
		builder.append(SQLUtils.FROM);
		builder.append(REQUESTS);
		builder.append(SQLUtils.WHERE);
		builder.append("ResourceID = '%s'");
		builder.append(SQLUtils.AND);
		builder.append("IncidentID = '%s'");
		

		String sql = String.format(builder.toString(), resourceId, incidentId);
		System.out.println(sql);
		jdbcTemplate.update(sql);
		
	}

	public void accept(Integer resourceId, Integer incidentId) {
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.UPDATE);
		builder.append(REQUESTS);
		builder.append(SQLUtils.SET);
		builder.append("Status = '%s'");
		builder.append(SQLUtils.WHERE);
		builder.append("ResourceID = '%s'");
		builder.append(SQLUtils.AND);
		builder.append("IncidentID = '%s'");
		

		String sql = String.format(builder.toString(), ACCEPTED, resourceId, incidentId);
		System.out.println(sql);
		jdbcTemplate.update(sql);
		
	}
	
	public void reject(Integer resourceId, Integer incidentId) {
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.UPDATE);
		builder.append(REQUESTS);
		builder.append(SQLUtils.SET);
		builder.append("Status = '%s'");
		builder.append(SQLUtils.WHERE);
		builder.append("ResourceID = '%s'");
		builder.append(SQLUtils.AND);
		builder.append("IncidentID = '%s'");
		

		String sql = String.format(builder.toString(), REJECTED, resourceId, incidentId);
		System.out.println(sql);
		jdbcTemplate.update(sql);
		
	}

}
