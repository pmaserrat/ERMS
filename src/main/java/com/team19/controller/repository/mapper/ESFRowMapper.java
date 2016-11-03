package com.team19.controller.repository.mapper;

import com.team19.controller.model.ESF;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by akeem on 11/2/16.
 */
public class ESFRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        ESF esf = new ESF();
        esf.setNumber(resultSet.getInt("Number"));
        esf.setDescription(resultSet.getString("Description"));
        return esf;
    }
}
