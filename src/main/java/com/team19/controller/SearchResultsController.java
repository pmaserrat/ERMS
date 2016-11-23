package com.team19.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.jdbc.Statement;
import com.team19.controller.Service.HttpSessionService;
import com.team19.controller.model.ESF;
import com.team19.controller.model.Resource;
import com.team19.controller.repository.ESFRepository;
import com.team19.controller.repository.IncidentRepository;
import com.team19.controller.repository.ResourceRepository;

import utils.SQLUtils;

@Controller
@RequestMapping("/searchResults/")
public class SearchResultsController {
	@Autowired
	HttpServletRequest request;

	@Autowired
	ESFRepository esfRepository;

	@Autowired
	ResourceRepository resourceRepository;
	
	@Autowired
	IncidentRepository incidentRepository;

	public static String RESOURCE = " Resource ";
	
	@RequestMapping(value = "searchResults", method = RequestMethod.POST)
	public String searchResults(Model model, @RequestParam Map<String, String> allRequestParams) {

		String sessionId = (String) request.getSession().getAttribute("user");
		Map<String, String[]> map = request.getParameterMap();
		
		System.out.println(sessionId);
		System.out.println(map);
		String keyword = allRequestParams.get("keyword");
		String primaryESFID = allRequestParams.get("PrimaryESF");
		String incidentID = allRequestParams.get("incident");		
		String distance = allRequestParams.get("distance");		
		
		System.out.println(keyword);
		System.out.println(primaryESFID);
		System.out.println(incidentID);
		System.out.println(distance);
		String sql = "SELECT Resource.ID, Resource.Name, Resource.Username, Resource.Amount, Resource.CostTimeUnit, Resource.Status "
				+ "FROM Resource "
				+ "LEFT OUTER JOIN Primary_ESF ON Primary_ESF.ResourceID = Resource.ID "
				+ "LEFT OUTER JOIN ESF ON Primary_ESF.Number = ESF.Number "
				+ "LEFT OUTER JOIN Capabilities ON Capabilities.ID = Resource.ID "
				+ "JOIN Incident "
				+ "WHERE (Primary_ESF.Number IS NULL OR Primary_ESF.Number = %s) "
				+ "OR (Resource.Name IS NULL OR Resource.Name LIKE '%%" + "%s" + "%%') "
				+ "OR (Resource.Model IS NULL OR Resource.Model LIKE '%%" + "%s" + "%%') "
				+ "OR (Capabilities.Capabilities IS NULL OR Capabilities.Capabilities LIKE '%%" + "%s" + "%%') "
				+ "OR (Incident.Description IS NULL OR Incident.Description = %s) ";
		System.out.println(sql);
		String format_sql = String.format(sql, primaryESFID, keyword, keyword, keyword, incidentID);
		System.out.println(format_sql);
		List<Resource> resources = resourceRepository.getSelectedResources(format_sql);
		model.addAttribute("resources", resources);
		return "searchResults";
	}
	
//	private Resource findResource(Map<String, String> allRequestParams) throws ParseException {
//		DecimalFormat format = new DecimalFormat("###.########");
//		format.setParseBigDecimal(true);
//		Resource resource = new Resource();
//		resource.setName(allRequestParams.get("resourceName"));
//		resource.setAmount(Double.parseDouble(allRequestParams.get("cost")));
//		resource.setModel(allRequestParams.get("model"));
//		resource.setLatitude(new BigDecimal(allRequestParams.get("Lat")));
//		resource.setLongitude(new BigDecimal(allRequestParams.get("Long")));
//		resource.setCostTimeUnit(allRequestParams.get("Unit"));
//		resource.setPrimaryESF(allRequestParams.get("PrimaryESF"));
//
//		return resource;
//
//	}
	
}