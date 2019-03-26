package com.ft.test.hello;

import org.springframework.aop.framework.ProxyFactory;

public class HelloService implements IHello {

    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
