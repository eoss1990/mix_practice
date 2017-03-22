package com.seeyon.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yangyu on 2017/2/28.
 */
public class LockTime {

    private final static Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {

        try {
            if (LOCK.tryLock(100, TimeUnit.MILLISECONDS)){
                System.out.println(111);

                //使用一个线程去拿锁，结果是没拿到
                new Thread(()->{
                    if (LOCK.tryLock()) System.out.println("线程拿到锁了");
                    else System.out.println("线程没拿到锁");
                }).start();

                Thread.sleep(1000*10);
            }else{
                System.out.println(222);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            LOCK.unlock();
        }
    }
}
