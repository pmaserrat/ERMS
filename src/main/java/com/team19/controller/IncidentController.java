package com.team19.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team19.controller.Service.HttpSessionService;
import com.team19.controller.model.Deployed;
import com.team19.controller.model.Incident;
import com.team19.controller.repository.MangementRepository;
import com.team19.controller.repository.IncidentRepository;
import com.team19.controller.repository.ResourceRepository;

/**
 * Created by akeem on 11/9/16.
 * 
 * Pmaserrat
 */
@Controller
@RequestMapping("/incidents/")
public class IncidentController {

	@Autowired
	IncidentRepository incidentRepository;
	
	@Autowired
	ResourceRepository resourceRepository;
	
	@Autowired
	MangementRepository deployedRepositry;

	@Autowired
	HttpServletRequest request;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(method = RequestMethod.GET)
	public String getIncidents(Model model) {
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		System.out.println(userName);
		model.addAttribute("incidents", incidentRepository.getAllIncidents(userName));
		model.addAttribute("resources", resourceRepository.getAvailableResources(userName));
		model.addAttribute("username", userName);
		return "incidents";
	}

	@RequestMapping(value = "addIncident", method = RequestMethod.GET)
	public String addIncidents(Model model) {
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		System.out.println(userName);
		
		model.addAttribute("username", userName);
		return "addIncident";
	}
	
	@RequestMapping(value = "deploy", method = RequestMethod.POST)
	public String deployIncident(Model model, @RequestParam Map<String, String> allRequestParams) {
		for (Map.Entry<String, String> entry : allRequestParams.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		Deployed deployed = new Deployed();
		deployed.setResourceID((Integer.parseInt(allRequestParams.get("ResourceID"))));
		deployed.setIncidentId((Integer.parseInt(allRequestParams.get("incident"))));
		deployed.setStartdate(new Timestamp(System.currentTimeMillis()));
		deployedRepositry.deploy(deployed);
		return "redirect:/incidents/";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addIncident(Model model, @RequestParam Map<String, String> allRequestParams) {
		System.out.println("addIncident post");
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		System.out.println(userName);
		System.out.println("param size: " + allRequestParams.size());
		for (Entry<String, String> entry : allRequestParams.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		try {
			Incident incident = create(allRequestParams, userName);
			incidentRepository.createIncident(incident);
		} catch (ParseException e) {
			System.out.println("error creating incident");
			e.printStackTrace();
			return "addIncident";
		}
		model.addAttribute("username", userName);
		return "redirect:/incidents/";
	}
	
	private Incident create(Map<String, String> allRequestParams, String userName) throws ParseException {
		Incident incident = new Incident();
		DecimalFormat format = new DecimalFormat("###.########");
		incident.setDescription(allRequestParams.get("description"));
		incident.setLatitude(new BigDecimal(allRequestParams.get("Lat")));
		incident.setLongitude(new BigDecimal(allRequestParams.get("Long")));
		Date date = dateFormat.parse(allRequestParams.get("Date"));
		Timestamp timestamp = new Timestamp(date.getTime());
		incident.setDate(timestamp);
		incident.setUsername(userName);
		return incident;
	}

}
