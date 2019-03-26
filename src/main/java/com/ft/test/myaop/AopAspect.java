package com.ft.test.myaop;

public class AopAspect {

    public void beforeHandler(String name, int age) {
        System.out.println("前置通知:" + name + ":age=" + age);
    }

    public void afterHandler() {
        System.out.println("后置通知");
    }

    public void returnHandler(Object name) { //在返回通知中获取返回值
        System.out.println("返回通知:" + name);
    }

    public void throwExceptionHandler(Throwable ex) {
        System.out.println("异常通知:" + ex.getMessage());
    }
}
