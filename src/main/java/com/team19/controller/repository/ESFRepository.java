package com.team19.controller.repository;

import com.team19.controller.model.ESF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by akeem on 11/2/16.
 */
@Repository
public class ESFRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<ESF> getAllESFs() {
        String sql = "SELECT * FROM  ESF";
        List<ESF> esfs = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for ( Map<String, Object> row: rows) {
            ESF esf = new ESF();
            esf.setNumber((Integer) row.get("Number"));
            esf.setDescription((String) row.get("Description"));
            esfs.add(esf);
        }

        return esfs;
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
