// Copyright (c) 2015‐2017 LIANHEBAOLI. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.2 : 2017-12-05, wanlin.liu, creation
// ============================================================================
package me.vanlin.cglib;

import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * author wanlin.liu
 *
 * @version 2.2.0
 */
public class CglibTest {
    static interface SampleInterface {
        String getValue();
        void setValue(String value);
    }
    static class SampleClass {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(final String value) {
            this.value = value;
        }
    }

    @Test
    public void testInterfaceMarker() {
        InterfaceMaker interfaceMaker = new InterfaceMaker();
        interfaceMaker.add(SampleClass.class);
        Class clazz = interfaceMaker.create();
        System.out.println(clazz.isInterface());
        System.out.println(clazz.getName());
        for (final Method method : clazz.getDeclaredMethods()) {
            System.out.println(method.getName() + " " + method.getReturnType().getSimpleName());
        }
    }

    @Test
    public void testEnhancer1() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleInterface.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy proxy) throws Throwable {
                System.out.println(method.getName() + " 0来了啊 " + args );
                return null;
            }
        });

        SampleInterface h1 = (SampleInterface) enhancer.create();
        h1.setValue("123");
        h1.getValue();
    }

    @Test
    public void testEnhancer2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleInterface.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
                final String methodName = method.getName();
                if (methodName.equals("toString")) {
                    return "Enchancer Proxy ";
                }
                System.out.println(proxy + method.getName() + " " + args);
                return null;
            }
        });

        SampleInterface h1 = (SampleInterface) enhancer.create();
        h1.setValue("123");
        h1.getValue();
    }

    @Test
    public void testEnhancer3() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleInterface.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "test loadObject  FixedValue";
            }
        });

        SampleInterface h1 = (SampleInterface) enhancer.create();
        System.out.println(h1.getValue());
    }
    @Test
    public void testEnhancer4() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleInterface.class);
        enhancer.setCallback(new Dispatcher() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("Dispatcher loadObject ...");

                return new SampleInterface() {
                    @Override
                    public String getValue() {
                        return "来了";
                    }

                    @Override
                    public void setValue(final String value) {

                    }
                };
            }
        });

        SampleInterface h1 = (SampleInterface) enhancer.create();
        System.out.println(h1.getValue());
        System.out.println(h1.getValue());
    }

    @Test
    public void testEnhancer5() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleInterface.class);
        enhancer.setCallback(new LazyLoader() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("LazyLoader in ... ");
                return new SampleInterface() {
                    @Override
                    public String getValue() {
                        return "来了";
                    }

                    @Override
                    public void setValue(final String value) {

                    }
                };
            }
        });

        SampleInterface h1 = (SampleInterface) enhancer.create();
        System.out.println(h1.getValue());
        System.out.println(h1.getValue());
    }

    @Test
    public void testEnhancer6() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleInterface.class);

        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(final Method method) {
                final String methodName = method.getName();
                int index = 0;
                switch(methodName) {
                    case "toString":
                    case "hashCode":
                    case "equals":
                    case "clone":
                        index = 0;
                        break;
                    case "getValue":
                        index = 1;
                        break;
                    default:
                        index = 2;
                }
                return index;
            }
        });
        enhancer.setCallbacks(new Callback[] {
                NoOp.INSTANCE,
                new FixedValue() {
                    @Override
                    public Object loadObject() throws Exception {
                        return "我是返回值";
                    }
                },
                new MethodInterceptor() {
                    @Override
                    public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy proxy) throws Throwable {
                        System.out.println(obj + " " + method.getName() + " " + args);
                        return null;
                    }
                }
        });
        SampleInterface h1 = (SampleInterface) enhancer.create();
        System.out.println(h1.getValue());
        System.out.println(h1.getValue());
        h1.setValue("hello world");
    }
}
