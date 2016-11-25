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
import com.team19.controller.repository.SQLUtils;

@Controller
@RequestMapping("/searchResource/")
public class SearchResourceController {
	@Autowired
	HttpServletRequest request;

	@Autowired
	ESFRepository esfRepository;

	@Autowired
	ResourceRepository resourceRepository;
	
	@Autowired
	IncidentRepository incidentRepository;

	public static String RESOURCE = " Resource ";
	
	@RequestMapping(method = RequestMethod.GET)
	public String searchResources(Model model) {
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		//String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		//System.out.println(userName);
		//model.addAttribute("username", userName);
		model.addAttribute("resources", resourceRepository.getAllResources());
		model.addAttribute("esfs", esfRepository.getAllESFs());
		model.addAttribute("incidents", incidentRepository.getAllIncidents());
		
		return "searchResource";
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
