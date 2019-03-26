package com.ft.test.myaop1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AopMessage {
    public void print(String method) {
        System.out.println("=================================");
        try {
            Method md = this.getClass().getMethod(method);
            System.out.print(md.getName() + "===");
            try {
                md.invoke(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public void mybefore() {
        System.out.println("this is appear before");
    }
    public void myafter() {
        System.out.println("this is appear after");
    }
    public void myafterreturn() {
        System.out.println("this is appear after return");
    }
    public void myafterthrow() {
        System.out.println("this is appear after throw");
    }
}
