package com.team19.controller.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Statement;
import com.team19.controller.model.Deployed;
import com.team19.controller.model.Resource;
import com.team19.controller.model.Schedules_Repair;

import utils.SQLUtils;

@Repository
public class MangementRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static final String  DEPLOYED = "Deployed";
	public static final String  SCHENDULES_REPAIR = "Schedules_Repair";
	
	@Transactional
	public void deploy(final Deployed deployed) {

		
			StringBuilder builder = new StringBuilder();
			builder.append(SQLUtils.INSERT_INTO);
			builder.append(DEPLOYED);
			builder.append("(IncidentID, ResourceID, StartDate)");
			builder.append(SQLUtils.VALUES + "(?, ?, ?)");
			String sql = builder.toString();
			
			
			jdbcTemplate.update(sql, new Object[] { deployed.getIncidentId(), deployed.getResourceID(), deployed.getStartdate() });
			
			
			StringBuilder updateResource = new StringBuilder();
			updateResource.append(SQLUtils.UPDATE);
			updateResource.append(ResourceRepository.RESOURCE);
			updateResource.append(SQLUtils.SET);
			updateResource.append("Status = '%s'");
			updateResource.append(SQLUtils.WHERE);
			updateResource.append("ID = " + deployed.getResourceID());
			sql = String.format(updateResource.toString(), Resource.DEPLOYED);
			
			jdbcTemplate.update(sql);
			
			
		}
	
	@Transactional
	public void repair(final Schedules_Repair repair) {

		
			StringBuilder builder = new StringBuilder();
			builder.append(SQLUtils.INSERT_INTO);
			builder.append(SCHENDULES_REPAIR);
			builder.append("(Username, ResourceID, RepairStartDate, DaysInRepair)");
			builder.append(SQLUtils.VALUES + "(?, ?, ?, ?)");
			String sql = builder.toString();
			
			
			jdbcTemplate.update(sql, new Object[] { repair.getUsername(), repair.getResourceId(), repair.getRepairStartDate(), repair.getDaysInRepair() });
			
			
			StringBuilder updateResource = new StringBuilder();
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, repair.getDaysInRepair());
			Date tomorrow = calendar.getTime();
			final Timestamp replairDate = new Timestamp(tomorrow.getTime());
			
			updateResource.append(SQLUtils.UPDATE);
			updateResource.append(ResourceRepository.RESOURCE);
			updateResource.append(SQLUtils.SET);
			updateResource.append("Status = ?,");
			updateResource.append("NextAvailableDate = ?");
			updateResource.append(SQLUtils.WHERE);
			updateResource.append("ID = ?");
			final String sql2 = updateResource.toString();
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
					PreparedStatement ps = arg0.prepareStatement(sql2);
					ps.setString(1, Resource.IN_REPAIR);
					ps.setTimestamp(2, replairDate);
					ps.setInt(3, repair.getResourceId());
					return ps;
				}
			});
			
		}

}
