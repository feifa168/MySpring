package com.ft;

import com.ft.test.ConstructBean;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConstructBean {
    @Test
    public void testMultiConstruct() {
        AbstractApplicationContext aac = new ClassPathXmlApplicationContext("multiconstructor.xml");
        // 自动加载多构造函数，如果不设置具体的参数则会自动找与参数名相同的bean的id名，设置具体参数则以设置的为准
        ConstructBean cb = (ConstructBean)aac.getBean("byconstructormulti");
        System.out.println(cb.toString());
        cb = (ConstructBean)aac.getBean("byconstructormulti2");
        System.out.println(cb.toString());
        aac.registerShutdownHook();
    }
}
