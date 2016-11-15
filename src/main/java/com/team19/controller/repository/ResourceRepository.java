package com.team19.controller.repository;

import com.team19.controller.model.Resource;

import utils.SQLUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by akeem on 11/5/16.
 */
@Repository
public class ResourceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public static String RESOURCE = "Resource";
    

    public List<Resource> getAllResources(String userName) {

        List<Resource> resources = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(SQLUtils.SELECT);
        builder.append("*");
        builder.append(SQLUtils.FROM);
        builder.append(RESOURCE);
        builder.append(SQLUtils.WHERE);
        builder.append("Username = '%s'");
        
        String sql = String.format(builder.toString(), userName);

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for ( Map<String, Object> row: rows) {
            Resource resource = new Resource();
            resource.setID((Integer) row.get("ID"));
            resource.setName((String) row.get("name"));
            resources.add(resource);
        }

        return resources;

    }


}
