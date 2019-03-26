package com.ft;

import com.ft.test.EatFood;
import com.ft.test.Food;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEatFood {
    @Test
    public void testByName() {
        AbstractApplicationContext aac = new ClassPathXmlApplicationContext("eatfood.xml");
        // byname与bytype的区别是byname是要求bean其中有一个id与对象的属性名一样，
        // 而bytype则是要求只要bean中能找到一个类型与对象set方法参数类型一样的，id名不需要与属性名一样，而且只能有一个这种类型的bean
        // 不管byname还是bytype，该类都必须提供set属性，否则抛出异常说找不到set方法
        EatFood ef = (EatFood) aac.getBean("byname");
        Food fd = ef.getFd();
        fd.show();

        //自己设置参数不受上面的限制，bena的id名可以任意，只要类型一样就行
        ef = (EatFood) aac.getBean("byname2");
        fd = ef.getFd();
        fd.show();
    }

    @Test
    public void testByType() {
        AbstractApplicationContext aac = new ClassPathXmlApplicationContext("eatfood.xml");
        EatFood ef;
        Food fd;
        // bytepe完全默认只能有一个相同类型的bean，而且只能与属性字段名一样
//        ef = (EatFood) aac.getBean("bytype");
//        fd = ef.getFd();
//        fd.show();

        //自己设置参数不受上面的限制，bena的id名可以任意，只要类型一样就行
        ef = (EatFood) aac.getBean("bytype2");
        fd = ef.getFd();
        fd.show();
    }

    @Test
    public void testByConstructor() {
        AbstractApplicationContext aac = new ClassPathXmlApplicationContext("eatfood.xml");
        // byconstructor要求bean中能找到id名与构造函数参数名一样的
        EatFood ef;
        Food fd;
        ef = (EatFood) aac.getBean("byconstructor");
        fd = ef.getFd();
        fd.show();

        //自己设置构造参数不受上面的限制，bena的id名可以任意，只要类型一样就行
        ef = (EatFood) aac.getBean("byconstructor2");
        fd = ef.getFd();
        fd.show();
    }
}
