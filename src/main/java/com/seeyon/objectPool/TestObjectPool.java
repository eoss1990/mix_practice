package com.seeyon.objectPool;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * Created by yangyu on 16/12/26.
 */

/**
 * org.apache.commons.pool2.ObjectPool对象池的使用
 * 直接apache使用对象池，可以实现对象池化技术
 */
public class TestObjectPool {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 创建对象工厂
         */
        ObjectFactory objectFactory = new ObjectFactory();
        /**
         * 创建一个普通的对象池
         */
        ObjectPool<ObjectFactory.Computer> objectPool = new GenericObjectPool(objectFactory);

        /**
         * 创建第一个线程
         */
        Thread t1 = new Thread(() -> {
            try {
                /**
                 * 从对象池中获取对象
                 */
                ObjectFactory.Computer computer = objectPool.borrowObject();
                System.out.println(Thread.currentThread().getId() + ":" + computer);
                /**
                 * 用完后将对象放回对象池中
                 */
                objectPool.returnObject(computer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t1.join();

        new Thread(() -> {
            try {
                /**
                 * 再次从对象池中获取对象，你会发现获取的是同一个对象，说明对象池化技术成功
                 */
                System.out.println(Thread.currentThread().getId() + ":" + objectPool.borrowObject());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
}
