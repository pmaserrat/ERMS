package com.team19.controller.Service;

import com.team19.controller.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by akeem on 11/14/16.
 */
public class HttpSessionService {
    private Map<String, User> httpSession;
    private static HttpSessionService sessionService;
    private  HttpSessionService() {

    }

    public static synchronized HttpSessionService getInstance() {
        if(sessionService == null) {
            sessionService = new HttpSessionService();
            sessionService.httpSession = new HashMap<>();
        }
        
        return sessionService;
    }

    public String createSession(User user) {
    	String sessionID = UUID.randomUUID().toString();
    	httpSession.put(sessionID, user);
    	return sessionID;
    }
    
    public void destroySession(String key) {
    	httpSession.remove(key);
    }
    
    public User getUsersession(String key){
    	return this.httpSession.get(key);
    }
    



}
