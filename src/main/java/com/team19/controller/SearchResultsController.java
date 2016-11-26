package com.team19.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.team19.controller.model.Requests;
import com.team19.controller.model.Incident;
import com.team19.controller.model.Resource;
import com.team19.controller.model.SearchedResource;
import com.team19.controller.repository.ESFRepository;
import com.team19.controller.repository.IncidentRepository;
import com.team19.controller.repository.RequestsResposiotry;
import com.team19.controller.repository.ResourceRepository;
import com.team19.controller.repository.SQLUtils;

@Controller
@RequestMapping("/searchResults/")
public class SearchResultsController {

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	HttpServletRequest request;

	@Autowired
	ESFRepository esfRepository;

	@Autowired
	ResourceRepository resourceRepository;
	
	@Autowired
	IncidentRepository incidentRepository;

	@Autowired
	RequestsResposiotry requestRepository;

	public static String RESOURCE = " Resource ";
	
	@RequestMapping(value = "searchResults", method = RequestMethod.GET)
	public String searchResults(Model model, @RequestParam Map<String, String> allRequestParams) {

		String sessionId = (String) request.getSession().getAttribute("user");
		Map<String, String[]> map = request.getParameterMap();
		
		System.out.println(sessionId);
		System.out.println(map);
		String keyword = allRequestParams.get("keyword");
		String primaryESFID = allRequestParams.get("PrimaryESF");
		String incidentparam = allRequestParams.get("incident");
		String incidentID = "";
		String incidentDescription = "";
		if (!incidentparam.isEmpty()) {
			String[] incidentArray = incidentparam.split("=");
			incidentID = incidentArray[0];
			incidentDescription = incidentArray[1];
			System.out.println(incidentID);
		}

		String distance = allRequestParams.get("distance");		
		String sql = "";
		System.out.println(keyword);
		System.out.println(primaryESFID);
		System.out.println(distance);
		
		sql = "SELECT DISTINCT Resource.ID as resourceID, Resource.Name, Resource.Username, Resource.Amount, Resource.CostTimeUnit, Resource.Status, "
				+ "Resource.NextAvailableDate, Resource.Latitude AS rlatitude, Resource.Longitude AS rlongitude, Incident.Latitude AS ilatitude, Incident.Longitude AS ilongitude "
				+ "FROM Resource "
				+ "JOIN Incident ";
		
		//Have esfnumber, esfdescription, keyword, and incident description from app
		//Get ID, Name of resource, Owner of resource, Cost of resource, Status, Next Available, Distance
		if (keyword.isEmpty() && primaryESFID.isEmpty() && incidentID.isEmpty() && distance.isEmpty()) {
			List<SearchedResource> resources = resourceRepository.getSelectedResources(incidentID, primaryESFID, keyword, distance);
			model.addAttribute("resources", resources);
		} else {
					
			//Build table joins first
			if (!incidentID.isEmpty() || !distance.isEmpty()) {
				model.addAttribute("incidentID", incidentID);
				model.addAttribute("incidentDes", incidentDescription);
			}

			if (!keyword.isEmpty()) {
				String join_capabilities = "LEFT OUTER JOIN Capabilities ON Capabilities.ID = resourceID ";
				sql = sql + join_capabilities;
			}
					
			if (!primaryESFID.isEmpty()) {
				String join_ESF = "LEFT OUTER JOIN Primary_ESF ON Primary_ESF.ResourceID = resourceID ";
				sql = sql + join_ESF;
			}
					
			sql = sql + "WHERE ";
					
			//Deal with queries
			if (!keyword.isEmpty()) {
				String resource_name_query = "Resource.Name LIKE '%" + keyword + "%' ";
				String resource_model_query = "OR Resource.Model LIKE '%" + keyword + "%' ";
				String capabilities_query = "OR Capabilities.Capabilities LIKE '%" + keyword + "%' ";
				sql = sql + "(" + resource_name_query + resource_model_query + capabilities_query + ")";
			}
					
			if (!primaryESFID.isEmpty()) {
				if (keyword.isEmpty()) {
					String primary_ESF_query = "Primary_ESF.Number = " + primaryESFID + " ";
					sql = sql + primary_ESF_query;
				}
				else {
					String primary_ESF_query = "AND Primary_ESF.Number = " + primaryESFID + " ";
					sql = sql + primary_ESF_query;
				}
			}
					
			if (!incidentID.isEmpty()) {
				if (keyword.isEmpty() && primaryESFID.isEmpty() && distance.isEmpty()) {
					String incident_query = "Incident.ID = " + incidentID;
					sql = sql + incident_query;
				}
				else {
					String incident_query = "AND Incident.ID = " + incidentID;
					sql = sql + incident_query;
				}
			}
					
					
			System.out.println(sql);
		}	
		List<SearchedResource> resources = resourceRepository.getSelectedResources(incidentID, primaryESFID, keyword, distance);
		System.out.println(resources);
		
		//Filter resources if distance is set
		for (SearchedResource resource : resources) {
			BigDecimal resource_lat = resource.getRLatitude();
			BigDecimal resource_lon = resource.getRLongitude();
			BigDecimal incident_lat = resource.getILatitude();
			BigDecimal incident_lon = resource.getILongitude();
			System.out.println("R Latitude: " + resource_lat + " R Longitude: " + resource_lon);
			System.out.println("I Latitude: " + incident_lat + " I Longitude: " + incident_lon);
		} 
				

		System.out.println(resources);
		model.addAttribute("resources", resources);
		model.addAttribute("incidentID", incidentID);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		model.addAttribute("username", userName);
		return "searchResults";
	}
	
	@RequestMapping(value = "request", method = RequestMethod.POST)
	public String createRequest(Model model, @RequestParam Map<String, String> allRequestParams) {

		for (Map.Entry<String, String> entry : allRequestParams.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		String param = allRequestParams.get("resource");
		Integer resourceID = null;
		Integer incidentID = null;
		Timestamp returnDate = null;
		String sessionId = (String) request.getSession().getAttribute("user");
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		String owner = null;
		if(param != null) {
			resourceID = Integer.parseInt(param);
		}
		param = allRequestParams.get("incident");
		if(param != null) {
			incidentID = Integer.parseInt(param);
		}
		param = allRequestParams.get("Date");
		if(param != null) {
			Date date = null;
			try {
				date = dateFormat.parse(param);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			returnDate = new Timestamp(date.getTime());
		}
		param = allRequestParams.get("owner");
		if (param != null) {
			owner = param;
		}
		Requests requests = new Requests();
		requests.setIncidentID(incidentID);
		requests.setResourceID(resourceID);
		requests.setReturnDate(returnDate);
		requests.setRequestDate(new Timestamp(System.currentTimeMillis()));
		requests.setSubmitter(userName);
		requests.setResourceOwner(owner);
		requestRepository.save(requests);
		System.out.println(requests);
		return "redirect:/searchResource/";
	}
	
}