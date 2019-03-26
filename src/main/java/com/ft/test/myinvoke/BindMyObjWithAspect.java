package com.ft.test.myinvoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindMyObjWithAspect {
    private String aspectCls;
    private String bf;
    private String af;
    private Class[] bfParamTypes;
    private Class[] afParamTypes;

    private String mycls;
    private String methodName;
    private Class[] myclsParamType;

    public Class[] getBfParamTypes() {
        return bfParamTypes;
    }

    public void setBfParamTypes(Class[] bfParamTypes) {
        this.bfParamTypes = bfParamTypes;
    }

    public Class[] getAfParamTypes() {
        return afParamTypes;
    }

    public void setAfParamTypes(Class[] afParamTypes) {
        this.afParamTypes = afParamTypes;
    }

    public String getAspectCls() {
        return aspectCls;
    }

    public void setAspectCls(String aspectCls) {
        this.aspectCls = aspectCls;
    }

    public String getBf() {
        return bf;
    }

    public void setBf(String bf) {
        this.bf = bf;
    }

    public String getAf() {
        return af;
    }

    public void setAf(String af) {
        this.af = af;
    }

    public String getMycls() {
        return mycls;
    }

    public void setMycls(String mycls) {
        this.mycls = mycls;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getMyclsParamType() {
        return myclsParamType;
    }

    public void setMyclsParamType(Class[] myclsParamType) {
        this.myclsParamType = myclsParamType;
    }

    public void handle() {
        try {
            Class<?> cls = Class.forName(mycls);
            try {
                Object myobj = cls.newInstance();
                try {
                    Method m = cls.getMethod(methodName, myclsParamType);
                    try {
                        Class<?> clsAspect = Class.forName(aspectCls);
                        Object objAspect = clsAspect.newInstance();
                        Method bfM = clsAspect.getMethod(bf, bfParamTypes);
                        bfM.invoke(objAspect, bfParamTypes);
                        System.out.println("现在调用方法"+m.getName());
                        // 插入注册的before函数
                        m.invoke(myobj, myclsParamType);
                        // 插入注册的after函数
                        Method afM = clsAspect.getMethod(af, afParamTypes);
                        afM.invoke(objAspect, afParamTypes);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
