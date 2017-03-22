package com.seeyon.classloader;

/**
 * Created by yangyu on 16/10/24.
 */
public class test {

    public static void main(String[] args) {
        MyClassLoader cl = new MyClassLoader();
        System.out.println(cl.getParent());
        System.out.println(cl.getParent().getParent());
        System.out.println(cl.getParent().getParent().getParent());
    }
}
