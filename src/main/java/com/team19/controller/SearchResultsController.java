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
import java.util.Collections;
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

		String keyword = allRequestParams.get("keyword");
		String primaryESFID = allRequestParams.get("PrimaryESF");
		String incidentparam = allRequestParams.get("incident");
		String incidentID = null;
		if (!incidentparam.isEmpty()) {
			String[] incidentArray = incidentparam.split("=");
			incidentID = incidentArray[0];
		}

		String distance = allRequestParams.get("distance");
		double incident_lat = 0;
		double incident_lon = 0;
		List<Resource> resources = resourceRepository.getSelectedResources(incidentID, primaryESFID, keyword, distance);
		if (incidentID != null) {
			Incident incident = incidentRepository.getSearchedIncident(incidentID);

			incident_lat = Math.toRadians(incident.getLatitude().doubleValue());
			incident_lon = Math.toRadians(incident.getLongitude().doubleValue());

			double lat_diff;
			double long_diff;
			double a;
			double c;
			double d = 0;
			List<Resource> resources_del = new ArrayList<>();
			// Filter resources if distance is set
			for (Resource resource : resources) {
				double resource_lat = Math.toRadians(resource.getLatitude().doubleValue());
				double resource_lon = Math.toRadians(resource.getLongitude().doubleValue());

				lat_diff = incident_lat - resource_lat;
				long_diff = incident_lon - resource_lon;
				a = Math.pow(Math.sin(lat_diff / 2), 2)
						+ Math.cos(resource_lat) * Math.cos(incident_lat) * Math.pow(Math.sin(long_diff / 2), 2);
				c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
				d = 6371 * c;

				resource.setDistance(d);
				if (resource.getDistance() > Double.parseDouble(distance)) {
					resources_del.add(resource);
				}
				// Calculate distances between each resource and incident
			}
			for (Resource resource : resources_del) {
				resources.remove(resource);
			}
		}
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