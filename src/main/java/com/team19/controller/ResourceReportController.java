package com.team19.controller;

import com.team19.controller.Service.HttpSessionService;
import com.team19.controller.model.Incident;
import com.team19.controller.repository.IncidentRepository;
import com.team19.controller.repository.ResourceReportRepository;
import com.team19.controller.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Created by Booker on 11/24/16.
 */
@Controller
@RequestMapping("/resourceReport/")
public class ResourceReportController {

    @Autowired
    ResourceReportRepository resourceReportRepository;

    @Autowired
    HttpServletRequest request;
    //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(method = RequestMethod.GET)
    public String getResourceReport(Model model) {
        String sessionId = (String) request.getSession().getAttribute("user");
        System.out.println(sessionId);
        String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
        System.out.println(userName);
        model.addAttribute("resourceReport", resourceReportRepository.getResourceReport(userName));
        model.addAttribute("username", userName);
        return "resourceReport";
    }

}

