package com.seeyon.redis.se.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangyu on 16/10/18.
 */
public class ThreadPoolSingleton {

    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    private ThreadPoolSingleton(){};

    public static void excute(Runnable runnable){
        cachedThreadPool.execute(runnable);
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId());
            }
        };

        cachedThreadPool.execute(runnable);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cachedThreadPool.execute(runnable);
    }
}
