package com.team19.controller.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Statement;
import com.team19.controller.model.Deployed;
import com.team19.controller.model.DeployedResource;
import com.team19.controller.model.ESF;
import com.team19.controller.model.Resource;

/**
 * Created by akeem on 11/5/16.
 */
@Repository
public class ResourceRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static String RESOURCE = " Resource ";

	public List<Resource> getAllResources() {

		List<Resource> resources = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.SELECT);
		builder.append("*");
		builder.append(SQLUtils.FROM);
		builder.append(RESOURCE);

		String sql = builder.toString();
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			Resource resource = new Resource();
			resource.setID((Integer) row.get("ID"));
			resource.setName((String) row.get("name"));
			resource.setStatus((String) row.get("status"));
			resource.setLongitude((BigDecimal) row.get("longitude"));
			resource.setLatitude((BigDecimal) row.get("latitude"));
			BigDecimal amt = (BigDecimal) row.get("amount");
			resource.setAmount(amt.doubleValue());
			resource.setCostTimeUnit((String) row.get("costTimeUnit"));
			resource.setModel((String) row.get("model"));
			resource.setNextAvailableDate((Timestamp) row.get("nextAvailableDate"));
			resources.add(resource);
		}
			
		return resources;

	}
	
	public List<Resource> getAllResources(String userName) {

		List<Resource> resources = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.SELECT);
		builder.append("*");
		builder.append(SQLUtils.FROM);
		builder.append(RESOURCE);
		builder.append(SQLUtils.WHERE);
		builder.append("Username = '%s'");

		String sql = String.format(builder.toString(), userName);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			Resource resource = new Resource();
			resource.setID((Integer) row.get("ID"));
			resource.setName((String) row.get("name"));
			resource.setStatus((String) row.get("status"));
			resource.setLongitude((BigDecimal) row.get("longitude"));
			resource.setLatitude((BigDecimal) row.get("latitude"));
			BigDecimal amt = (BigDecimal) row.get("amount");
			resource.setAmount(amt.doubleValue());
			resource.setCostTimeUnit((String) row.get("costTimeUnit"));
			resource.setModel((String) row.get("model"));
			resource.setNextAvailableDate((Timestamp) row.get("nextAvailableDate"));
			resources.add(resource);
		}

		return resources;

	}
	

	public List<Resource> getSelectedResources(String incidentID, String primaryESFID, String keyword , String distance) {
		

		List<Resource> resources = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.SELECT);
		builder.append("Resource.ID, Resource.Name, Resource.Username, Resource.Amount, Resource.CostTimeUnit, Resource.Status");
		builder.append(SQLUtils.FROM);
		builder.append(RESOURCE);
		builder.append(SQLUtils.JOIN + "Primary_ESF" + SQLUtils.ON + "Primary_ESF.ResourceID = Resource.ID");
		builder.append(SQLUtils.JOIN + "ESF" + SQLUtils.ON + "Primary_ESF.Number = ESF.Number");
		builder.append(SQLUtils.WHERE);
		builder.append("(Primary_ESF.Number IS NULL OR Primary_ESF.Number = %s)");
		builder.append(SQLUtils.OR);
		builder.append("(Resource.Name IS NULL OR Resource.Name LIKE '%%" + "%s" + "%%')");
		builder.append(SQLUtils.OR);
		builder.append("(Resource.Model IS NULL OR Resource.Model LIKE '%%" + "%s" + "%%')");
		builder.append(SQLUtils.OR);
		builder.append("Resource.ID IN (SELECT ID FROM  `Capabilities` WHERE Capabilities.Capabilities like '%%" + "%s" + "%%')");
		builder.append(SQLUtils.OR);
		builder.append("EXISTS (SELECT Number FROM  `Additional_ESF` WHERE Number = %s AND Additional_ESF.ResourceId = Resource.ID) ");
		String format_sql = String.format(builder.toString(), primaryESFID, keyword, keyword, keyword, primaryESFID);
		System.out.println(format_sql);
		//Need to get resource ID, esfnumber, esfdescription, keyword, and incident description from app
				
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(format_sql);
		for (Map<String, Object> row : rows) {
			Resource resource = new Resource();
			resource.setID((Integer) row.get("ID"));
			resource.setName((String) row.get("Name"));
			resource.setUsername((String) row.get("Username"));
			resource.setStatus((String) row.get("Status"));
			BigDecimal amt = (BigDecimal) row.get("Amount");
			if(amt != null) {
				resource.setAmount(amt.doubleValue());
			}
			resource.setCostTimeUnit((String) row.get("CostTimeUnit"));
			

			resources.add(resource);
		}
		return resources;
	}
	
	public List<DeployedResource> getDeployedlResources(String userName) {
		List<DeployedResource> resources = new ArrayList<>();
		
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.SELECT);
		builder.append("D.ResourceID as ResourceID, R.Username,name,NextAvailableDate,Status,Model,R.Latitude as R_Latittude,R.Longitude as R_longitude ,Amount,CostTimeUnit, D.IncidentID AS IncidentID, I.Description AS I_Description");
		builder.append(SQLUtils.FROM);
		builder.append(RESOURCE + "AS R");
		builder.append(SQLUtils.INNER_JOIN + "Deployed AS D");
		builder.append(SQLUtils.ON + "R.ID = D.ResourceID");
		builder.append(SQLUtils.INNER_JOIN + "Incident AS I");
		builder.append(SQLUtils.ON + "I.ID = D.IncidentID");
		builder.append(SQLUtils.WHERE);
		builder.append("R.Username = '%s'");
		String sql = String.format(builder.toString(), userName);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			DeployedResource resource = new DeployedResource();
			resource.setResourceID((Integer) row.get("ResourceID"));
			resource.setIncidentID((Integer) row.get("IncidentID"));
			resource.setName((String) row.get("name"));
			resource.setDescription((String) row.get("I_Description"));
			resource.setStatus((String) row.get("Status"));
			resource.setLongitude((BigDecimal) row.get("Longitude"));
			resource.setLatitude((BigDecimal) row.get("R_Latittude"));
			BigDecimal amt = (BigDecimal) row.get("amount");
			resource.setAmount(amt.doubleValue());
			resource.setCostTimeUnit((String) row.get("costTimeUnit"));
			resource.setModel((String) row.get("model"));
			resource.setNextAvailableDate((Timestamp) row.get("nextAvailableDate"));
			resources.add(resource);
		}

		return resources;
	}
	
	public List<Resource> getInRepairlResources(String userName) {
		List<Resource> resources  = new ArrayList<>();
		
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.SELECT);
		builder.append("ID, R.Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit");
		builder.append(SQLUtils.FROM);
		builder.append(RESOURCE + "AS R");
		builder.append(SQLUtils.INNER_JOIN + "Schedules_Repair AS S");
		builder.append(SQLUtils.ON + "R.ID = S.ResourceID");
		builder.append(SQLUtils.WHERE);
		builder.append("R.Username = '%s';");
		String sql = String.format(builder.toString(), userName);
		System.out.println(sql);
	
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			Resource resource = new Resource();
			resource.setID((Integer) row.get("ID"));
			resource.setName((String) row.get("name"));
			resource.setStatus((String) row.get("status"));
			resource.setLongitude((BigDecimal) row.get("longitude"));
			resource.setLatitude((BigDecimal) row.get("latitude"));
			BigDecimal amt = (BigDecimal) row.get("amount");
			resource.setAmount(amt.doubleValue());
			resource.setCostTimeUnit((String) row.get("costTimeUnit"));
			resource.setModel((String) row.get("model"));
			resource.setNextAvailableDate((Timestamp) row.get("nextAvailableDate"));
			resources.add(resource);
		}
		System.out.println(resources.size());
		return resources;
	}
	
	
	public List<Resource> getAvailableResources(String userName) {
		List<Resource> resources = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.SELECT);
		builder.append("ID, Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit");
		builder.append(SQLUtils.FROM);
		builder.append(RESOURCE + "AS R");
		builder.append(SQLUtils.WHERE);
		builder.append("R.ID NOT IN (SELECT ResourceID FROM `Deployed` UNION SELECT ResourceID FROM  `Schedules_Repair` )");
		builder.append(SQLUtils.AND);
		builder.append("Username = '%s';");
		
		String sql = String.format(builder.toString(), userName);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> row : rows) {
			Resource resource = new Resource();
			resource.setID((Integer) row.get("ID"));
			resource.setName((String) row.get("name"));
			resource.setStatus((String) row.get("status"));
			resource.setLongitude((BigDecimal) row.get("longitude"));
			resource.setLatitude((BigDecimal) row.get("latitude"));
			BigDecimal amt = (BigDecimal) row.get("amount");
			resource.setAmount(amt.doubleValue());
			resource.setCostTimeUnit((String) row.get("costTimeUnit"));
			resource.setModel((String) row.get("model"));
			resource.setNextAvailableDate((Timestamp) row.get("nextAvailableDate"));
			resources.add(resource);
		}

		return resources;
	}
		
	@Transactional
	public Integer createResource(final Resource resource) {

		Integer ID = 0;
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.INSERT_INTO);
		builder.append(RESOURCE);
		builder.append("(Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit)");
		builder.append(SQLUtils.VALUES + "(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		final String sql = builder.toString();
		System.out.println(sql);
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, resource.getUsername());
				ps.setString(2, resource.getName());
				ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
				ps.setString(4, Resource.READY);
				ps.setString(5,  resource.getModel());
				ps.setBigDecimal(6, resource.getLatitude());
				ps.setBigDecimal(7, resource.getLongitude());
				ps.setDouble(8, resource.getAmount());
				ps.setString(9, resource.getCostTimeUnit());
				return ps;
			}
		}, holder );
		
		Integer id = holder.getKey().intValue();
		//Insert capabilites
		StringBuilder capabilitySQL = new StringBuilder();
		capabilitySQL.append(SQLUtils.INSERT_INTO);
		capabilitySQL.append("Capabilities");
		capabilitySQL.append("(ID, Capabilities)");
		capabilitySQL.append(SQLUtils.VALUES + "(?, ?)");
		 String sql2 = capabilitySQL.toString();
		for (String capability : resource.getCapabilities()) {

			jdbcTemplate.update(sql2, new Object[] { id, capability });
		}
		//insert Primary ESF
		StringBuilder primaryESFSQL = new StringBuilder();
		primaryESFSQL.append(SQLUtils.INSERT_INTO);
		primaryESFSQL.append("Primary_ESF");
		primaryESFSQL.append("(	Number, 	ResourceId)");
		primaryESFSQL.append(SQLUtils.VALUES + "(?, ?)");
		final String sql3 = primaryESFSQL.toString();
		
		jdbcTemplate.update(sql3, new Object[] { Integer.parseInt(resource.getPrimaryESF()), id });
		
		//insert Additional esf
		for (ESF esf : resource.getAdditonalESF()) {
			StringBuilder addtionalESFSQL= new StringBuilder();
			addtionalESFSQL.append(SQLUtils.INSERT_INTO);
			addtionalESFSQL.append("Additional_ESF");
			addtionalESFSQL.append("(	Number, 	ResourceId)");
			addtionalESFSQL.append(SQLUtils.VALUES + "(?, ?)");
			String sql4 = addtionalESFSQL.toString();
			jdbcTemplate.update(sql4, new Object[] { esf.getNumber(), id });
		}

		return id;

	}

}
