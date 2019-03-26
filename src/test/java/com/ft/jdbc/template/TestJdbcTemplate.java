package com.ft.jdbc.template;

import com.ft.test.jdbctemplate.Employee;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class TestJdbcTemplate {
    static private JdbcTemplate jdbcTemplate;

    static {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://172.16.39.251:12239/test?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void testTemplate1() {
        List<Map<String, Object> > employee =  jdbcTemplate.queryForList("select * from employees");
        System.out.println(employee);
    }
    @Test
    public void testTemplate2() {
        List<Map<String, Object> > employee =  jdbcTemplate.queryForList("select * from employees where id = ?", 1L);
        System.out.println(employee);
    }
    @Test
    public void testTemplateNull() {
        List<Map<String, Object> > employee =  jdbcTemplate.queryForList("select * from employees where id = ?", new Object[]{2});
        System.out.println(employee);
    }
    @Test
    public void testTemplate3() {
        List<Map<String, Object> > employee =  jdbcTemplate.queryForList("select * from employees where id = ?", new Object[]{3});
        System.out.println(employee);
    }

    @Test
    public void testRowMapper() {
        List<Employee> employees = jdbcTemplate.query("select * from employees", (ResultSet resultSet, int i)->{
            Employee emp = new Employee();
            emp.setId(resultSet.getInt("id"));
            emp.setAge(resultSet.getInt("age"));
            emp.setFirst(resultSet.getString("first"));
            emp.setLast(resultSet.getString("last"));
            return emp;
        });
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    @Test
    public void testInsert() {
        jdbcTemplate.update("insert into employees(age, first, last) values(?,?,?)", 22, "\'tom22\'", "\'tom20_20\'");
    }
    @Test
    public void testCallableStatement() {
        String firstName = jdbcTemplate.execute(new CallableStatementCreator() {
                                                    @Override
                                                    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
                                                        CallableStatement cs = connection.prepareCall("{call getEmpName(?,?)}");
                                                        cs.setInt(1, 1);
                                                        cs.registerOutParameter(2, Types.VARCHAR);
                                                        return cs;
                                                    }
                                                },
                new CallableStatementCallback<String>() {
                    @Override
                    public String doInCallableStatement(CallableStatement callableStatement) throws SQLException, DataAccessException {
                        callableStatement.execute();
                        return  callableStatement.getString(2);
                    }
                });
        System.out.println(firstName);
    }
}