package com.seeyon.queue;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by yangyu on 16/11/27.
 */
public class TestLinkedBlockingQueue {

    public static LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                try {
                    while (true){
                        System.out.println(Thread.currentThread().getId()+":"+linkedBlockingQueue.take());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("线程池启动完毕");

        String str="";
        InputStreamReader in;
        BufferedReader input;
        while (true)
        {

            in = new InputStreamReader(System.in);
            input = new BufferedReader(in);
            try {
                str = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if ("exit".equals(str))
                break;
            linkedBlockingQueue.offer(str);
        }

        try {
            in.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testRemove(){
        linkedBlockingQueue.offer("yangyu");
        linkedBlockingQueue.offer("linling");
        System.out.println("offer done");

        boolean result = linkedBlockingQueue.remove("lalala");
        System.out.println("remove done:"+result);
    }

}
