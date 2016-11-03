package com.team19.controller;

import com.team19.controller.model.ESF;
import com.team19.controller.model.User;
import com.team19.controller.repository.ESFRepository;
import com.team19.controller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by akeem on 11/2/16.
 */
@RequestMapping("/rest/")
@org.springframework.web.bind.annotation.RestController
public class RestServiceController {

    @Autowired
    private ESFRepository esfRepository;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/esfs")
    @ResponseBody
    public List<ESF> getAllESFs() {
        return esfRepository.getAllESFs();
    }


    @RequestMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
