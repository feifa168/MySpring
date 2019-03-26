package com.ft.jdbc.template;

import com.ft.test.jdbctemplate.Employee;
import com.ft.test.jdbctemplate.JdbcEmployee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.List;

public class TestC3P0 {
    static JdbcEmployee jdbcEmployee;
    static {
        String xmlFile = "c3p0.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlFile);
        jdbcEmployee = (JdbcEmployee) context.getBean("jdbcEmployee");
    }

    @Test
    public void testQueryAll() {
        List<Employee> employees = jdbcEmployee.queryAll();
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
    @Test
    public void testQueryOne() {
        jdbcEmployee.queryById(1);
    }
    @Test
    public void testInsert() {
        jdbcEmployee.insert();
    }
}
