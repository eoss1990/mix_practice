package com.seeyon.proxy;

import sun.misc.ProxyGenerator;

import java.lang.reflect.*;

/**
 * Created by yangyu on 16/12/5.
 */
public class TestJdkProxy {
    public static void main(String[] args) {

        BeanInterFace bean = new Bean();
        InvocationHandler invocationHandler = new MyProxy(bean);

        String proxyName = "com.seeyon.proxy.BeanProxy0";
        Class[] interfaces = {BeanInterFace.class};
        int accessFlags = Modifier.PUBLIC | Modifier.FINAL;

        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces, accessFlags);

        MyClassLoader myClassLoader = new MyClassLoader();
        Class proxyClazz = myClassLoader.defineClass(proxyName,proxyClassFile);
        System.out.println(proxyClazz.getClassLoader());
        final Constructor<?> cons;
        try {
            cons = proxyClazz.getConstructor(InvocationHandler.class);
            BeanInterFace proxyBean = (BeanInterFace) cons.newInstance(new Object[]{invocationHandler});
            proxyBean.say();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static class MyClassLoader extends ClassLoader{

        public Class defineClass(String className , byte[] data){
            return super.defineClass(className,data,0,data.length);
        }
    }
}
