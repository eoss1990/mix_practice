package com.seeyon.thread;

import org.junit.Test;

import static java.lang.Thread.sleep;

/**
 * Created by yangyu on 16/9/24.
 */
public class MyWaitNotify {

    public class Monitor{

    }

    Monitor monitorObject = new Monitor();
    boolean waitSign = false;

    public void doWait(){
        synchronized (monitorObject){
            while (!waitSign)
            {
                try {
                    monitorObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doNotify(){
        synchronized (monitorObject){
            waitSign=true;
            monitorObject.notify();
        }
    }

    public void doNotifyAll(){
        synchronized (monitorObject){
            waitSign=true;
            monitorObject.notifyAll();
        }
    }

    @Test
    public void testNotify(){

        new Thread(){
            public void run(){
                try {
                    System.out.println("线程1:启动");
                    sleep(5000);
                    System.out.println("线程1:notify；");
                    doNotify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程结束");
            }
        }.start();

        System.out.println("主线程wait");
        doWait();
        System.out.println("主线程被激活");
    }

    @Test
    public void testNotifyAll() {

        new Thread(){
            public void run(){
                try {
                    System.out.println("线程1:启动");
                    sleep(5000);
                    System.out.println("线程1:notifyAll；");
                    doNotifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1：结束");
            }
        }.start();

        new Thread(){
            public void run(){
                System.out.println("线程2：等待");
                doWait();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2：激活");
                System.out.println("线程2：结束");
            }
        }.start();


        System.out.println("主线程wait");
        doWait();
        System.out.println("主线程：激活");
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程：完成");
    }

//    @Test
//    public void testString(){
//        String s1 = "a";
//        String s2 = s1+"b";
//        String s3 = "a"+"b";
//
//        System.out.println("ab".equals(s2));
//        System.out.println(s3=="ab");
//    }

}
