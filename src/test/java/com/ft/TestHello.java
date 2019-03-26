package com.ft;

import com.ft.test.hello.IHello;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHello {
    @Test
    public void testHelloAop() {
        AbstractApplicationContext aac = new ClassPathXmlApplicationContext("hello.xml");
        IHello helloService = aac.getBean("helloService",IHello.class);
        helloService.sayHello();
        aac.registerShutdownHook();
    }
}
