package com.team19.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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
import com.team19.controller.model.ESF;
import com.team19.controller.model.Resource;
import com.team19.controller.repository.CostTimeUnitRepository;
import com.team19.controller.repository.ESFRepository;
import com.team19.controller.repository.ResourceRepository;

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

	@Autowired
	ResourceRepository ressourceRepository;
	@Autowired
	CostTimeUnitRepository costimeUnitREpository;


	@RequestMapping(method = RequestMethod.GET)
	public String addResources(Model model) {
		String sessionId = (String) request.getSession().getAttribute("user");
		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		System.out.println(userName);
		model.addAttribute("username", userName);
		model.addAttribute("esfs", esfRepository.getAllESFs());

		model.addAttribute("units", costimeUnitREpository.getCostTimeUnits());

		return "addResource";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addResources(Model model, @RequestParam Map<String, String> allRequestParams) {



		String sessionId = (String) request.getSession().getAttribute("user");
		Map<String, String[]> map = request.getParameterMap();

		System.out.println(sessionId);
		String userName = HttpSessionService.getInstance().getUsersession(sessionId).getUserName();
		try {
			Resource resource = createResource(allRequestParams, userName);
			resource.setCapabilities(new ArrayList<String>());
			resource.getCapabilities().addAll(getCapabilities(map));
			resource.setAdditonalESF(new ArrayList<ESF>());
			resource.getAdditonalESF().addAll(getAdditionalESFs(map));
			ressourceRepository.createResource(resource);
			System.out.println(resource.toString());
		} catch (NumberFormatException| ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("error creating resource");
			return "addResource";
		}
		System.out.println(userName);
		model.addAttribute("username", userName);
		return "redirect:/resource";
	}

	private Resource createResource(Map<String, String> allRequestParams, String userName) throws ParseException {
		DecimalFormat format = new DecimalFormat("###.########");
		format.setParseBigDecimal(true);
		Resource resource = new Resource();
		resource.setName(allRequestParams.get("resourceName"));
		resource.setUsername(userName);
		resource.setAmount(Double.parseDouble(allRequestParams.get("cost")));
		resource.setModel(allRequestParams.get("model"));
		resource.setLatitude(new BigDecimal(allRequestParams.get("Lat")));
		resource.setLongitude(new BigDecimal(allRequestParams.get("Long")));
		resource.setCostTimeUnit(allRequestParams.get("Unit"));
		resource.setPrimaryESF(allRequestParams.get("PrimaryESF"));

		return resource;

	}
	
	private List<ESF> getAdditionalESFs(Map<String, String[]> map) {
		List<ESF> esfs = new ArrayList<>();
		for (Entry<String, String[]> entry : map.entrySet()) {
			System.out.println(entry);
			String[] fields = entry.getKey().split("=");
			if (fields[0].equalsIgnoreCase("Addriontal")) {
				for (int i = 0; i < entry.getValue().length; i++) {
					ESF esf = new ESF();
					esf.setNumber(Integer.parseInt(entry.getValue()[i]));
					esfs.add(esf);
				}
			}

		}
		
		return esfs;
	}

	private List<String> getCapabilities(Map<String, String[]> map) {
		List<String> capabilities = new ArrayList<>();
		for (Entry<String, String[]> entry : map.entrySet()) {
			System.out.println(entry);
			String[] fields = entry.getKey().split("=");
			if (fields[0].equalsIgnoreCase("capability")) {
				for (int i = 0; i < entry.getValue().length; i++) {
					capabilities.add(entry.getValue()[i]);
				}
			}

		}

		return capabilities;
	}

}
