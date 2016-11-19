package com.team19.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team19.controller.Service.HttpSessionService;
import com.team19.controller.repository.ESFRepository;

/**
 * Created by akeem on 11/9/16.
 * 
 * Pmaserrat
 */
@Controller
@RequestMapping("/addResource/")
public class AddResourceController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	ESFRepository esfRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String addResources(Model model) {
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		System.out.println(userName);
		model.addAttribute("username", userName);
		model.addAttribute("esfs", esfRepository.getAllESFs());
		return "addResource";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addResources(Model model, @RequestParam Map<String, String> allRequestParams) {
		for (Map.Entry<String, String> entry : allRequestParams.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		System.out.println(userName);
		model.addAttribute("username", userName);
		return "redirect:/resource";
	}
}
