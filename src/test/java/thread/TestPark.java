package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by yangyu on 16/10/17.
 */
public class TestPark {
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            System.out.println("阻塞线程1");
            LockSupport.park();
            System.out.println("线程1执行完啦");
        });

        t.start();

        try {
            Thread.sleep(2000);
            System.out.println("唤醒线程1");
            LockSupport.unpark(t);
            Thread.sleep(2000);
            System.out.println("主线程结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
