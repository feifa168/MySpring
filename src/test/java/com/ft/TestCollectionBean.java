package com.ft;

import com.ft.test.CollectionBean;
import com.ft.test.Person;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.Set;

public class TestCollectionBean {
    @Test
    public void testCollection() {
        AbstractApplicationContext aac = new ClassPathXmlApplicationContext("bean.xml");
        CollectionBean cb = (CollectionBean)aac.getBean("collection");
        System.out.println(cb.getListInt());
        System.out.println(cb.getSetInt());
        Map<Integer, Person> m = cb.getMapIntStr();
        for (Map.Entry<Integer, Person> entry : m.entrySet()) {
            System.out.println(entry.getKey()+"="+entry.getValue().getName());
        }
        System.out.println(cb.getProt());
    }
}
