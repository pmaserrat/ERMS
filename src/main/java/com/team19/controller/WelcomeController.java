/**
 * 
 */
package com.team19.controller;

import com.team19.controller.model.User;
import com.team19.controller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Set;

/**
 * @author Pmaserrat
 * 
 *         Add your names
 * 
 * @date Oct 31, 2016
 */
@Controller
public class WelcomeController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public ModelAndView helloWorld(@RequestParam Map<String,String> allRequestParams) throws Exception {

		for (Map.Entry<String, String>entry :allRequestParams.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		String username = allRequestParams.get("username");
		String password = allRequestParams.get("password");

		User user = userRepository.getUserbyUserName(username);
		if(user == null) {
			throw new Exception("Invalid Username and/or Password.");
		}
		if(user.getPassword().equals(password)) {
			ModelAndView model = new ModelAndView();

			model.addObject("username", user.getUserName());
			return model;
		} else {
			throw new Exception("Invalid Username and/or Password.");
		}


	}

}
