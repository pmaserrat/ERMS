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
import com.team19.controller.model.ESF;
import com.team19.controller.model.Resource;

import utils.SQLUtils;

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
				ps.setString(4, "Ready");
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
