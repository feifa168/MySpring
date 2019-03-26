package com.ft;

import com.ft.test.myaop.MyAop;
import com.ft.test.myaop.MyAopImpl;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop2 {
    private AbstractApplicationContext aac = null;

    public void init() {
        aac = new ClassPathXmlApplicationContext("myaop2.xml");
    }

    public void uninit() {
        aac.registerShutdownHook();
    }

    public void TestAop2() {
        init();
    }

    @Test
    public void testAop123() {
        if (aac == null) {
            init();
        }
        MyAop ma = aac.getBean("myaop2", MyAop.class);
        ma.show("tom", 20);
        MyAop mai = (MyAop) ma;
        mai.show("jerry", 22);

        // java.lang.ClassCastException: com.sun.proxy.$Proxy7 cannot be cast to com.ft.test.myaop.MyAopImpl
//        MyAopImpl mb = (MyAopImpl) aac.getBean("myaop2", MyAopImpl.class);
//        mb.show("tom", 20);
        uninit();
    }
}
