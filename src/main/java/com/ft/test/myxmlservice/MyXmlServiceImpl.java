package com.ft.test.myxmlservice;

public class MyXmlServiceImpl implements MyXmlService {

    public String handlerXml(String name, int age) {
        //int a = 1 / 0; //异常通知模拟
        System.out.println(name + ":xml处理类!");
        return name;
    }
}
