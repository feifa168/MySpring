package com.ft;

import com.ft.test.myxmlservice.MyXmlService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMyXmlService {
    @Test
    public void test12() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        MyXmlService service = (MyXmlService) context.getBean("myXmlService");
        String name = service.handlerXml("t6", 25);
        name = service.handlerXml("mmmm", 30);
        System.out.println(name);
    }
}
