package deep;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.sleep;

/**
 * Created by yangyu on 16/9/29.
 */
public class Deep {

    public boolean bool = true;

    public static String[] str;

    private final Map<String,String> map = new ConcurrentHashMap<String, String>(15);

    private final String abc = "1";


    public static void main(String[] args) {

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("yangyu","aaaa");
        concurrentHashMap.put("yangyu","bbbb");

//        HashMap map = new HashMap();
//        map.put("1","mapper");
//        map.put("2","bbb");
//        map.put("3","bbb");
//        map.put("4","bbb");
//        map.put("5","bbb");
//        map.put("6","bbb");
//        map.put("7","bbb");
//        map.put("8","bbb");
//        map.put("9","bbb");
//        map.put("10","bbb");
//        map.put("11","bbb");
//        map.put("12","bbb");
//        map.put("13","bbb");
//        map.put("14","bbb");

    }

    @Test
    public void testFinal(){

    }


    public void test() {



        new Thread(new Runnable() {
            @Override
            public void run() {
                while (bool)
                {
                    System.out.println("thread1");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (bool)
                {
                    System.out.println("thread2");
                }
            }
        }).start();

        try {
            sleep(1000);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bool=false;
                    System.out.println("update flag----------------------------------");
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void getA(){
        System.out.println(11);
    }

}
