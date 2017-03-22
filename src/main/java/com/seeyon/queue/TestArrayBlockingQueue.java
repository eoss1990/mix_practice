package com.seeyon.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by yangyu on 16/11/27.
 */

/**
 * ArrayBlockingQueue是数组结构组成的有界阻塞队列
 * 当队列已经满了的时候，put操作会阻塞当前线程，直到队列发生出队操作然后会唤醒put线程在入队
 * 当队列为空的时候，take操作会阻塞当前线程，直到队列发生入队操作后会唤醒take线程进行出队
 */
public class TestArrayBlockingQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue(1);

        try {
            queue.put("1111");
            /**
             * 该操作会被阻塞，知道队列发生出队操作
             */
            queue.put("2222");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
