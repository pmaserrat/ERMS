package com.team19.controller.repository;

import com.team19.controller.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by akeem on 11/2/16.
 */
@Repository
public class UserRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<User> getAllUsers() {
        String sql = "\n" +
                "\n" +
                "SELECT User.Username, User.Name, Individual.JobTitle, Individual.DateHired, Government_Agency.Jurisdiction, Company.HQLocation, Municipalities.Population\n" +
                "FROM User\n" +
                "LEFT JOIN Individual ON User.Username = Individual.Username\n" +
                "LEFT JOIN Government_Agency ON User.Username = Government_Agency.Username\n" +
                "LEFT JOIN Company ON User.Username = Company.Username\n" +
                "LEFT JOIN Municipalities ON User.Username = Municipalities.Username\n";
        List<User> users = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for ( Map<String, Object> row: rows) {

            String name = (String) row.get("Name") ;
            String userName = (String) row.get("Username");

            Object value = row.get("HQLocation");
            if(value != null) {

                Company user = new Company();
                user.setUserName(userName);
                user.setName(name);
                user.sethQLocation((String) value);
                users.add(user);

            }

            value = row.get("Population");
            if(value != null) {
                Municipality user = new Municipality();
                user.setUserName(userName);
                user.setName(name);
                user.setPopulation(Integer.parseInt((String)value));
                users.add(user);

            }

            value = row.get("JobTitle");
            if(value != null) {
                Individual user = new Individual();
                user.setUserName(userName);
                user.setName(name);
                user.setJobTitle((String) row.get("JobTitle"));
                value = row.get("DateHired");
                if(value != null) {
                    user.setDateHired((Timestamp) row.get("DateHired"));
                }
                users.add(user);
            }

            value = row.get("Jurisdiction");
            if(value != null) {
                GovernmentAgency user = new GovernmentAgency();
                user.setUserName(userName);
                user.setName(name);
                user.setJurisdiction((String) value);
                users.add(user);
            }

        }

        return users;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
