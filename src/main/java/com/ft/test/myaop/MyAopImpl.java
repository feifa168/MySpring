package com.ft.test.myaop;

public class MyAopImpl implements MyAop {
    public String show(String name, int age) {
        System.out.println(name + ", " + age);
        return name;
    }
}
