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


	public Integer createResource(Resource resource) {

		Integer ID = 0;
		StringBuilder builder = new StringBuilder();
		builder.append(SQLUtils.INSERT_INTO);
		builder.append(RESOURCE);
		builder.append("(Username,name,NextAvailableDate,Status,Model,Latitude,Longitude,Amount,CostTimeUnit)");
		builder.append(SQLUtils.VALUES + "(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		String sql = builder.toString();
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(sql,
				new Object[] { resource.getUsername(), resource.getName(), resource.getNextAvailableDate(),
						resource.getStatus(), resource.getModel(), resource.getLatitude(), resource.getLongitude(),
						resource.getAmount(), resource.getCostTimeUnit() }, holder);

	return holder.getKey().intValue();
	}
	
}
