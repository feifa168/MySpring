package com.ft;

import com.ft.test.CollectionBean;
import com.ft.test.Person;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TestCollectionBean {
    private AbstractApplicationContext aac;

    public TestCollectionBean() {
        init();
    }

    public AbstractApplicationContext init() {
        aac = new ClassPathXmlApplicationContext("collection.xml");
        return aac;
    }

    public void uninit() {
        aac.registerShutdownHook();
    }

    public Method getThisMethod(String name) {
        Method md = null;
        try {
            Method[] mds = this.getClass().getDeclaredMethods();
            md = this.getClass().getDeclaredMethod(name, java.lang.String.class);
            md.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return md;
    }

    public void testAny(String fun, String bean) {
        System.out.println("=====================");
        Method md = getThisMethod(fun);
        TestCollectionBean tcb = new TestCollectionBean();

        try {
            md.invoke(this, bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        tcb.uninit();
    }

    private void testListInt(String bean) {
        CollectionBean cb = (CollectionBean)aac.getBean(bean);
        System.out.println(cb.getListInt());
    }

    private void testSetInt(String bean) {
        CollectionBean cb = (CollectionBean)aac.getBean(bean);
        Set<Object> si = cb.getSetInt();
        for (Object i : si) {
            System.out.print(i+",");
        }
    }
    private void testMapIntStr(String bean) {
        CollectionBean cb = (CollectionBean)aac.getBean(bean);
        Map<Integer, Person> mp = cb.getMapIntStr();
        for (Map.Entry<Integer, Person> me : mp.entrySet()) {
            System.out.println(me.getKey() + "=" + me.getValue().toString() + ", ");
        }
    }
    private void testProperty(String bean) {
        CollectionBean cb = (CollectionBean)aac.getBean(bean);
        Properties pts = cb.getProt();
        for (Map.Entry<Object, Object> pt :pts.entrySet()) {
            System.out.print(pt.getKey() + "=" + pt.getValue().toString() + ", ");
        }
        System.out.println(cb.getListInt());
    }
    private void testAllCollection(String bean) {
        CollectionBean cb = (CollectionBean)aac.getBean(bean);
        System.out.println(cb.getListInt());
        System.out.println(cb.getSetInt());
        Map<Integer, Person> m = cb.getMapIntStr();
        for (Map.Entry<Integer, Person> entry : m.entrySet()) {
            System.out.println(entry.getKey()+"="+entry.getValue().getName());
        }
        System.out.println(cb.getProt());
    }

    @Test
    public void testCollection() {
        testAny("testAllCollection", "collection");
    }

    @Test
    public void testList1() {
        testAny("testListInt", "list");
    }
    @Test
    public void testSet1() {
        testAny("testSetInt", "set");
    }
    @Test
    public void testMap1() {
        testAny("testMapIntStr", "map");
    }
    @Test
    public void testProperty1() {
        testAny("testProperty", "property");
    }
}
