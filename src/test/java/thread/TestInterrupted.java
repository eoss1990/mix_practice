package thread;

/**
 * Created by yangyu on 16/10/17.
 */
public class TestInterrupted {
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            try {
                for (; ; ) {
                    System.out.println("aaaa");
                    Thread.sleep(50);
                }

            } catch (InterruptedException e) {
                System.out.println("catch ex");

            }
        });
        t.start();

        try {
            Thread.sleep(1000);
            t.interrupt();
            System.out.println("中断线程----------");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
