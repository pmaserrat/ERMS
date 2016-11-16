package com.team19.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team19.controller.Service.HttpSessionService;
import com.team19.controller.repository.IncidentRepository;

@Controller
@RequestMapping("/incidents")
public class IncidentController {

	@Autowired
	IncidentRepository incidentRepository;

	@Autowired
	HttpServletRequest request;

	@RequestMapping(method = RequestMethod.GET)
	public String getIncidents(Model model) {
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		System.out.println(userName);
		model.addAttribute("incidents", incidentRepository.getAllIncidents(userName));
		model.addAttribute("username", userName);
		return "incidents";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addIncidents(Model model) {
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		System.out.println(userName);

		model.addAttribute("username", userName);
		return "addIncident";
	}

	@RequestMapping(value = "/MainMenu")
	public String mainMenu() {
		return "/welcome";
	}

}
