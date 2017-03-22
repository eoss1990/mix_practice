package com.seeyon.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Created by yangyu on 16/11/27.
 */

/**
 * DelayQueue是一个支持延时获取元素的无界队列
 * DelayQueue可以用于如下场景：
 * 1.缓存系统的的设计：用DelayQueue保存缓存元素的有效期，用一个线程循环查询DelayQueue，一旦能从DelayQueue中获取到元素，说明该元素过期了
 * 2.定时任务调度：使用DelayQueue保存当天会执行的任务和执行时间，一旦从DelayQueue中获取到任务就开始执行,TimerQueue就是使用DelayQueue实现的
 * DelayQueue的原理：
 * 1.当线程put元素的时候，DelayQueue会对你put的元素通过其本身的compareTo方法进行排序，延时时间越短的顺序越靠近队列头部
 * 2.当线程take元素的时候，DelayQueue会检测当前是否有Thread已经在等待队头元素了，如果有的话，那么只能阻塞当前前程，等已经取到队头
 * 的Thread完成以后再唤醒。
 * 如果没有Thread在等待队头元素的话，那么会查询一下队头元素还剩多少Delay时间，并且将当前线程设置为队头等待线程，然后让当前线程wait剩余
 * Delay时间后在来获取队头元素。
 */
public class TestDelayQueue {
    public static void main(String[] args) {
        DelayQueue<Message> delayQueue = new DelayQueue<>();
        delayQueue.put(new Message(2000,"yangyu"));

        try {
            System.out.println(delayQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }

    private static class Message implements Delayed{

        private long nanoTime;

        private String data;

        Message(long millisTime ,String data){
            this.nanoTime = now()+millisTime*(1000*1000);
            this.data = data;
        }

        private final long now(){
            return System.nanoTime();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(nanoTime - now(), NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed other) {
            if (other == this) // compare zero if same object
                return 0;
            if (other instanceof Message) {
                Message x = (Message) other;
                long diff = nanoTime - x.nanoTime;
                if (diff < 0)
                    return -1;
                else if (diff > 0)
                    return 1;
                else
                    return 1;
            }
            long diff = getDelay(NANOSECONDS) - other.getDelay(NANOSECONDS);
            return (diff < 0) ? -1 : (diff > 0) ? 1 : 0;
        }
    }
}
