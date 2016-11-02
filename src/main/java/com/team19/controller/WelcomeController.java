/**
 * 
 */
package com.team19.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Pmaserrat
 * 
 *         Add your names
 * 
 * @date Oct 31, 2016
 */
@Controller
public class WelcomeController {

	@RequestMapping("/welcome")
	public ModelAndView helloWorld(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) throws Exception {

		ModelAndView model = new ModelAndView();
		if (username.equals("admin") && password.equals("admin")) {
			model.addObject("username", username);
			return model;
		} else {
			throw new Exception("Invalid Username and/or Password.");
		}
	}

}
