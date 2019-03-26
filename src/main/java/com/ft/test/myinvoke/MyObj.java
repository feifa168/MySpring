package com.ft.test.myinvoke;

import java.util.Objects;

public class MyObj {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyObj{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
