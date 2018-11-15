package com.ft;

import com.ft.test.EatFood;
import com.ft.test.Food;
import com.ft.test.Person;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class TestPerson {
    @Test
    public void testBeanFactory() {
        // 不建议使用，已过时，此处纯粹是测试
        XmlBeanFactory xf = new XmlBeanFactory(new ClassPathResource("bean.xml"));
        Person p = (Person)xf.getBean("person");
        p.show();
    }

    @Test
    public void testPerson() {
        try {
            ApplicationContext apc;
            apc = new ClassPathXmlApplicationContext("bean.xml");
            // FileSystemXmlApplicationContext需要提供绝对路径文件名或相对路径名
            //apc = new FileSystemXmlApplicationContext(".\\src\\main\\resources\\bean.xml");

            Person p = (Person)apc.getBean("person");
            p.setName("tom");
            p.show();
            Person p11 = (Person)apc.getBean("person");
            p.show();

            //  scope默认是singleton，只第一次获取时创建对象，后面的都是第一次创建的对象
            System.out.println("============================");
            Person p2 = (Person)apc.getBean("personSingleton");
            p2.setName("hahahaha");
            p2.show();
            Person p21 = (Person)apc.getBean("personSingleton");
            p21.show();

            // scope设置为prototype，则每次调用getBean都会创建一个新的
            System.out.println("============================");
            Person p3 = (Person)apc.getBean("personPrototype");
            p3.setName("huhuhu");
            p3.show();
            Person p31 = (Person)apc.getBean("personPrototype");
            p31.show();

            System.out.println("============================");
            Person p4 = (Person)apc.getBean("personInitDestroy", Person.class);
            p4.setAge(26);
            p4.show();

            //((ClassPathXmlApplicationContext) apc).close();
            ((ClassPathXmlApplicationContext) apc).registerShutdownHook();
//    name is tom age is 20
//    name is tom age is 20
//            ============================
//    name is hahahaha age is 22
//    name is hahahaha age is 22
//            ============================
//    name is huhuhu age is 24
//    name is prototype age is 24

        } catch (BeansException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testEatFood() {
        AbstractApplicationContext aac = new ClassPathXmlApplicationContext("bean.xml");
        EatFood ef = (EatFood)aac.getBean("eatfood");
        Food fd = ef.getFd();
        System.out.println(fd.getName() + ", " + fd.getColor());

        EatFood ef2 = (EatFood)aac.getBean("eatfoodConstructor");
        Food fd2 = ef.getFd();
        System.out.println(fd2.getName() + ", " + fd2.getColor());

        aac.registerShutdownHook();
    }

    @Test
    public void testInOutBean() {
        AbstractApplicationContext aac = new ClassPathXmlApplicationContext("bean.xml");
        EatFood ef = (EatFood)aac.getBean("eatfoodOuterBean");
        Food fd = ef.getFd();
        System.out.println(fd.getName() + ", " + fd.getColor());

        aac.registerShutdownHook();
    }
}
