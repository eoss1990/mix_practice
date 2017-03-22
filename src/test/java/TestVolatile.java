import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by yangyu on 16/10/11.
 */
public class TestVolatile {

    public volatile static Vector<Integer> list = new Vector<Integer>();

    public static void main(String[] args) {
        // 初始化一个list，放入5个元素

        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        // 线程一：通过Iterator遍历List
        new Thread(new Runnable() {
            @Override
            public void run() {

                list.forEach(item -> {
                    System.out.println("遍历元素：" + item);
                    // 由于程序跑的太快，这里sleep了1秒来调慢程序的运行速度
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

//                for(int item : list) {
//                    System.out.println("遍历元素：" + item);
//                    // 由于程序跑的太快，这里sleep了1秒来调慢程序的运行速度
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }).start();

        new Thread(() -> System.out.println("lambda")).start();

        // 线程二：remove一个元素
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 由于程序跑的太快，这里sleep了1秒来调慢程序的运行速度
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                list.remove(4);
                System.out.println("list.remove(4)");
            }
        }).start();
    }
}
