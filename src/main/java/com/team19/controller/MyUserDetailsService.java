package com.team19.controller;

import com.team19.controller.model.User;
import com.team19.controller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by akeem on 11/9/16.
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            System.out.println("here here here");
            System.out.println("Username:" + username);

            User user = userRepository
                    .getUserbyUserName(username);

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            List<GrantedAuthority> authorities = buildUserAuthority("ADMIN");
            return buildUserForAuthentication(user, authorities);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            throw new UsernameNotFoundException(e.toString());
        }
    }


        private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
                List<GrantedAuthority> authorities) {
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), true, true,
                    true, true, authorities);
        }

        private List<GrantedAuthority> buildUserAuthority(String userRoles) {

            if (userRoles == null) {
                System.out.println("User Roles is null");
            }

            Set<GrantedAuthority> setAuths = new HashSet<>();

            // Build user's authorities

            setAuths.add(new SimpleGrantedAuthority(userRoles));

            List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
                    setAuths);

            return Result;
        }
    }

