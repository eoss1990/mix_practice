package com.seeyon.redis.se.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by yangyu on 16/12/2.
 */
public class FixThreadPoolTest {
    private static ExecutorService fixThreadPool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        fixThreadPool.execute(new run());
        LockSupport.park();
    }

    private static class run implements Runnable{
        @Override
        public void run() {
            System.out.println(1111);
        }
    }
}
