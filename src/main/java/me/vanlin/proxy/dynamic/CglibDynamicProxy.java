// Copyright (c) 2015‐2017 LIANHEBAOLI. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.2 : 2017-12-05, wanlin.liu, creation
// ============================================================================
package me.vanlin.proxy.dynamic;

import net.sf.cglib.beans.ImmutableBean;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * author wanlin.liu
 *
 * @version 2.2.0
 */
public class CglibDynamicProxy {
    public void test(){
        System.out.println("hello world");
    }

    static class SampleClass {
        public String test(String input){
            return "hello world";
        }
    }

    static class SampleBean {
        private String value;

        public SampleBean() {
        }

        public SampleBean(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        /*Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibDynamicProxy.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        CglibDynamicProxy sample = (CglibDynamicProxy) enhancer.create();
        sample.test();*/

        /*final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello world!";
            }
        });
        SampleClass sampleClass = (SampleClass) enhancer.create();
        System.out.println(sampleClass.test(""));
        System.out.println(sampleClass.toString());
        System.out.println(sampleClass.hashCode());*/

        SampleBean bean = new SampleBean();
        bean.setValue("Hello world");
        SampleBean immutableBean = (SampleBean) ImmutableBean.create(bean);
        System.out.println(immutableBean.getValue());
        bean.setValue("Hello world, again"); //可以通过底层对象来进行修改
        System.out.println(immutableBean.getValue());
        immutableBean.setValue("hhhhhh");
        System.out.println(immutableBean.getValue());
    }
}
