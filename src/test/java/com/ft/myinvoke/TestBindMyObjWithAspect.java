package com.ft.myinvoke;

import com.ft.test.myinvoke.BindMyObjWithAspect;
import com.ft.test.myinvoke.MyAspect;
import com.ft.test.myinvoke.MyObj;
import org.junit.Test;

import java.lang.reflect.Method;

public class TestBindMyObjWithAspect {
    @Test
    public void testMyAspect() {
        BindMyObjWithAspect ba = new BindMyObjWithAspect();
        ba.setMycls(MyObj.class.getName());
        Class<?> mycls = MyObj.class;
        Method[] ms = mycls.getDeclaredMethods();

        Class<?> specCls = MyAspect.class;

        for (Method m : ms) {
            Class[] paramTypes = m.getParameterTypes();
            // 过滤掉参数不为0的，因为是模拟，所以参数不明确，实际xml中可以配置这些参数，所以是明确的
            if (paramTypes.length != 0)
                continue;

            ba.setMethodName(m.getName());
            ba.setMyclsParamType(m.getParameterTypes());
            ba.setAspectCls(specCls.getName());
            ba.setBf("mybefore");
            ba.setAf("myafter");
            ba.handle();
        }
    }
}
