package com.team19.controller;

import com.team19.controller.Service.HttpSessionService;
import com.team19.controller.model.Resource;
import com.team19.controller.repository.ESFRepository;
import com.team19.controller.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by akeem on 11/9/16.
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    ResourceRepository resourceRepository;
    
    @Autowired
    ESFRepository esfRepository; //TODO: remove
    
    @Autowired
	HttpServletRequest request;

    @RequestMapping(method = RequestMethod.GET)
    public String getResources (Model model)
    {
    	String sessionId = (String) request.getSession().getAttribute("user");
    	System.out.println(sessionId);
    	String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
        System.out.println(userName);
        model.addAttribute("resources", resourceRepository.getAllResources(userName));
        model.addAttribute("esfs", esfRepository.getAllESFs());
        model.addAttribute("username", userName);
        return "resources";
    }

}
