/**
 * 
 */
package com.team19.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team19.controller.Service.HttpSessionService;
import com.team19.controller.model.User;
import com.team19.controller.repository.UserRepository;

/**
 * @author Pmaserrat
 * 
 * @date Oct 31, 2016
 */
@Controller
public class WelcomeController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public String welcome(@RequestParam Map<String, String> allRequestParams) throws Exception {

		for (Map.Entry<String, String> entry : allRequestParams.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		String username = allRequestParams.get("username");
		String password = allRequestParams.get("password");
		System.out.println(request);

		User user = userRepository.getUserbyUserName(username);
		if (user == null) {
			throw new Exception("Invalid Username and/or Password.");
		}
		if (user.getPassword().equals(password)) {
			ModelAndView model = new ModelAndView();

			model.addObject("username", user.getUserName());
			String sessionId = HttpSessionService.getInstance().createSession(user);
			request.setAttribute("user", sessionId);
			request.getSession().setAttribute("user", sessionId);
			request.setAttribute("userName", username);
			request.getSession().setAttribute("userName", username);
			return "welcome";
		} else {
			throw new Exception("Invalid Username and/or Password.");
		}

	}

	@RequestMapping(value = "/Logout", method = RequestMethod.POST)
	public String logout() {
		String sessionId = (String) request.getSession().getAttribute("user");
		HttpSessionService.getInstance().destroySession(sessionId);

		return "redirect:index.jsp";
	}

	@RequestMapping(value = "/mainMenu", method = RequestMethod.POST)
	public String mainMenu() {
		String userName = (String) request.getSession().getAttribute("userName");
		ModelAndView model = new ModelAndView();
		model.addObject("username", userName);
		return "welcome";
	}

}
