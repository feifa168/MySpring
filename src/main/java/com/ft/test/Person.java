package com.ft.test;

public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void show() {
        System.out.println("name is "+name+ " age is "+age);
    }

    public void init() {
        System.out.println("init");
    }
    public void destroy() {
        System.out.println("destroy");
    }
}
