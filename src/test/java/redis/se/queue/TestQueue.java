package redis.se.queue;

import com.seeyon.redis.se.queue.manager.QueueManager;
import com.seeyon.redis.se.queue.pojo.RequestPojo;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yangyu on 16/10/14.
 */
public class TestQueue {

    private static final String QUEUE_NAME = "se_queue";

    private static ApplicationContext applicationContext;

    private static QueueManager<RequestPojo> queueManager;

    private static volatile int flagStop = 0;

    private static int count = 0;

    private final static int TIME = 60000;

    private final static int PUSH_THREAD_COUNT=5;

    private final static int POP_THREAD_COUNT=3;

    static {
        applicationContext = new ClassPathXmlApplicationContext("beans-config.xml");
        queueManager = (QueueManager<RequestPojo>) applicationContext.getBean("queueManager");
    }


    public static void main(String[] args){

        System.out.println("测试开始-----------------------------");
        final CountDownLatch countDownLatch = new CountDownLatch(POP_THREAD_COUNT+PUSH_THREAD_COUNT);

        for (int i = 0; i < PUSH_THREAD_COUNT; i++) {
            new Thread(() -> {
                while (flagStop == 0) {
                    push(Integer.valueOf(getCountAndset()).toString());
                }
                countDownLatch.countDown();
            }).start();
        }

        for (int i = 0; i < POP_THREAD_COUNT; i++) {
            new Thread(() -> {
                while (true) {
                    if (null == pop() && flagStop == 1 ) {
                        break;
                    }
                }
                countDownLatch.countDown();
            }).start();
        }

        try {
            Thread.sleep(TIME);
            //System.out.println("测试时间到----------------");
            flagStop = 1;
            countDownLatch.await();
        } catch (InterruptedException e) {
            flagStop = 1;
            e.printStackTrace();
        }
        System.out.println("测试结束,存入时间："+(TIME/1000)+"S----------------------------");
        System.out.println("本次测试存入开启"+PUSH_THREAD_COUNT+"个线程，一共存入" + (count - 1) + "个请求");
        System.out.println("本次测试读取开启"+POP_THREAD_COUNT+"个线程");

    }

    public synchronized static int getCountAndset() {

        int tmp = count;
        count++;
        return tmp;

    }

    /***
     * 入队
     */
    public static void push(String flowId) {

        RequestPojo rp = new RequestPojo(flowId);
        queueManager.push(QUEUE_NAME, rp);
        //System.out.println("push:" + flowId);
    }

    /**
     * 出队
     */
    public static RequestPojo pop() {
        RequestPojo rp = queueManager.pop(QUEUE_NAME);
        System.out.println(Thread.currentThread().getName() + "pop:" + (rp == null ? "队列全部取完" : rp.getFlowId()));
        return rp;
    }


}
