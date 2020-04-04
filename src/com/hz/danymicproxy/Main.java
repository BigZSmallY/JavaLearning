package com.hz.danymicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        SayHello sayHello = new SayHello();
        Hello proxy = (Hello) Proxy.newProxyInstance(sayHello.getClass().getClassLoader(),
                sayHello.getClass().getInterfaces(), new LoggerInterceptor(sayHello));
        proxy.sayHello();

    }
}

class LoggerInterceptor implements InvocationHandler {
    private SayHello obj;

    LoggerInterceptor(SayHello instance) {
        obj = instance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin");
        method.invoke(obj, args);
        System.out.println("after");
        return null;
    }
}

class SayHello implements Hello{
    public void sayHello() {
        System.out.println("hello");
    }
}

interface Hello{
    void sayHello();
}
