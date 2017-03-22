package com.seeyon.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by yangyu on 16/11/27.
 */

/**
 * SynchronousQueue是一个不存储数据的队列，只是做数据的的传递工作
 * 同步队列，一个put操作必须等待一个take操作，否则线程被阻塞
 * 同样，一个take操作必须等待一个put操作，否则线程被阻塞
 */
public class TestSynchronousQueue {
    public static void main(String[] args) {
        SynchronousQueue<String> strings = new SynchronousQueue<>();
        Thread t =new Thread(()->{
            try {
                /**
                 * 该take操作会被阻塞，直到后面的strings.put("yangyu")操作后，当前线程才会被唤醒
                 */
                System.out.println(strings.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();


        try {
            Thread.sleep(2000);
            /**
             * 唤醒阻塞线程并且传递数据
             */
            strings.put("yangyu");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("完成");

    }
}
