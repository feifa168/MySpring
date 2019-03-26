package com.ft.test.myaop1;

import com.ft.test.Person;

public class AopBean {
    private String name;
    private Person ps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPs() {
        return ps;
    }

    public void setPs(Person ps) {
        this.ps = ps;
    }

    public void printThrowException(){
        System.out.println("Exception raised");
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "AopBean{" +
                "name='" + name + '\'' +
                ", ps=" + ps +
                '}';
    }
}
