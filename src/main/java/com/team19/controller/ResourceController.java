package com.team19.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team19.controller.Service.HttpSessionService;
import com.team19.controller.model.Resource;
import com.team19.controller.model.Schedules_Repair;
import com.team19.controller.repository.ESFRepository;
import com.team19.controller.repository.MangementRepository;
import com.team19.controller.repository.RequestsResposiotry;
import com.team19.controller.repository.ResourceRepository;

/**
 * Created by akeem on 11/9/16.
 * 
 * Pmaserrat
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	ResourceRepository resourceRepository;

	@Autowired
	ESFRepository esfRepository; // TODO: remove
	
	@Autowired
	RequestsResposiotry requestRepository;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	MangementRepository mangementRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String getResources(Model model) {
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		System.out.println(userName);
		model.addAttribute("resources", resourceRepository.getAvailableResources(userName));
		model.addAttribute("inRepairs", resourceRepository.getInRepairlResources(userName));
		model.addAttribute("deployedResources", resourceRepository.getDeployedlResources(userName));
		model.addAttribute("esfs", esfRepository.getAllESFs());
		model.addAttribute("username", userName);
		return "resources";
	}
	
	
	@RequestMapping(value= "/manage", method = RequestMethod.GET)
	public String getResourceStatus(Model model) {
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		model.addAttribute("requests", requestRepository.getAllRequests(userName));
		System.out.println("returning to view");
		return "ManageResources";
		
	}
	
	@RequestMapping(value= "/repair", method = RequestMethod.POST)
	public String repair(Model model, @RequestParam Map<String, String> allRequestParams) {
		for (Map.Entry<String, String> entry : allRequestParams.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		Schedules_Repair repair = new Schedules_Repair();
		repair.setResourceId(Integer.parseInt(allRequestParams.get("resource")));
		repair.setUsername(userName);
		repair.setDaysInRepair(10);
		repair.setRepairStartDate(new Timestamp(System.currentTimeMillis()));
		mangementRepository.repair(repair);

		System.out.println("returning to view");
		return "redirect:/resource/";
		
	}
	
	@RequestMapping(value= "/return", method = RequestMethod.POST)
	public String cancel(Model model, @RequestParam Map<String, String> allRequestParams) {
		
		for (Map.Entry<String, String> entry : allRequestParams.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		
		Integer resourceId = -1;
		Integer incident = -1;
		String id = allRequestParams.get("resource");
		if(id != null && !"".equals(id)) {
			resourceId = Integer.parseInt(id);
		}
		id = allRequestParams.get("incident");
		if(id != null && !"".equals(id)) {
			incident = Integer.parseInt(id);
		}
		if(resourceId != -1 && incident != -1) {
			mangementRepository.cancelDeploy(resourceId, incident);
		} else if (resourceId != -1 && incident == -1) {
			mangementRepository.cancelRepair(resourceId);
		}
		


		System.out.println("returning to view");
		return "redirect:/resource/";
		
	}

}
