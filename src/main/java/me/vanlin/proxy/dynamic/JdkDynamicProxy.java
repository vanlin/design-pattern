// Copyright (c) 2015‐2017 LIANHEBAOLI. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.2 : 2017-12-05, wanlin.liu, creation
// ============================================================================
package me.vanlin.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理
 * author wanlin.liu
 *
 * @version 2.2.0
 */
public class JdkDynamicProxy {

    /**
     * Handler
     */
    static class DynamicProxyHandler implements InvocationHandler {

        @Override
        public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
            System.out.println(method.getName() + "来了");
            return null;
        }
    }

    /**
     * 创建代理对象
     * @param clazz
     * @param handler
     * @param <T>
     * @return
     */
    static <T extends Object>  T createProxyObject(Class<T> clazz, DynamicProxyHandler handler) {
        final Object reObj = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{clazz},
                handler);
        return (T)reObj;
    }

    static interface IHello {
        void sayHello();
    }
    static class World {
        public String sayHi() {
            final String world = "world";
            return world;
        }
    }

    public static void main(String[] args) {
        final DynamicProxyHandler handler = new DynamicProxyHandler();
        IHello hello = JdkDynamicProxy.createProxyObject(IHello.class, handler);

        hello.sayHello();
    }

}
