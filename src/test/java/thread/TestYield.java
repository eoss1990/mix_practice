package thread;

import static java.lang.Thread.sleep;

/**
 * Created by yangyu on 16/9/30.
 */
public class TestYield implements Runnable {
    @Override
    public void run() {
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+":结束");
    }

    public static void main(String[] args) {
        TestYield runn = new TestYield();
        Thread t1 = new Thread(runn,"First");
        Thread t2 = new Thread(runn,"Second");

        t1.start();
        t2.start();
    }
}
