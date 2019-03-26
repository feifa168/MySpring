package com.ft.test.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JdbcEmployee {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert() {
        jdbcTemplate.update("insert into employees(age, first, last) values(?,?,?)", 30, "first30", "last30");
    }

    public void queryById(int id) {
        Map<String, Object> emp = jdbcTemplate.queryForMap("select * from employees where id=?", id);
        System.out.println(emp);
    }

    public List<Employee> queryAll() {
        return jdbcTemplate.query("select * from employees", new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                Employee emp = new Employee();
                emp.setId(resultSet.getInt("id"));
                emp.setAge(resultSet.getInt("age"));
                emp.setFirst(resultSet.getString("first"));
                emp.setLast(resultSet.getString("last"));
                return emp;
            }
        });
    }
}
