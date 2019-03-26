package com.ft;

import com.ft.test.calc.ArithmeticCalculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCalcAspect {
    @Test
    public void test23423() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-aspectj-xml.xml");
        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");

        System.out.println(arithmeticCalculator.getClass().getName());

        int result = arithmeticCalculator.add(1, 2);
        System.out.println("result:" + result);

        result = arithmeticCalculator.div(1000, 0);//这里故意除以0 让程序出错，让切面得到异常通知
        System.out.println("result:" + result);
    }
}
