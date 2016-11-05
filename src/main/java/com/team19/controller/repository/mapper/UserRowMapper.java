package com.team19.controller.repository.mapper;

import com.team19.controller.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by akeem on 11/3/16.
 */
public class UserRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserName(resultSet.getString("Username"));
        user.setPassword(resultSet.getString("Password"));
        return user;
    }
}
