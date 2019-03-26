package com.ft;

import com.ft.test.myaop1.AopBean;
import com.ft.test.myaop1.AopMessage;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {
    private AbstractApplicationContext aac;

    public AbstractApplicationContext init() {
        aac = new ClassPathXmlApplicationContext("myaop.xml");
        return aac;
    }

    public void uninit() {
        aac.registerShutdownHook();
    }

    public void testAspect() {
    }

    public TestAop() {
        init();
    }

    @Test
    public void testAopMessage() {
        AopMessage am = new AopMessage();
        am.print("mybefore");
        am.print("myafter");
        am.print("myafterreturn");
        am.print("myafterthrow");
    }

    @Test
    public void testAopBean() {
        AopBean ab = (AopBean)aac.getBean("aopb");
        ab.getName();
//        System.out.println(ab.getName() + ", " + ab.getPs().toString());
//        ab.printThrowException();
        System.out.print(ab.toString());
    }

}
