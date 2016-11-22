package com.team19.controller.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.team19.controller.model.CostTimeUnit;

@Repository
public class CostTimeUnitRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<CostTimeUnit> getCostTimeUnits() {
		List<CostTimeUnit> costTimeUnits = new ArrayList<>();
		 String sql = "SELECT * FROM  Cost_Time_Unit";
		 
		 List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	        for ( Map<String, Object> row: rows) {
	        	CostTimeUnit costTimeUnit = new CostTimeUnit();
	        	costTimeUnit.setUnit((String) row.get("Unit"));
	        	costTimeUnits.add(costTimeUnit);
	        }
		
		
		
		return costTimeUnits;
	}

}
