package jvm;

/**
 * Created by yangyu on 16/11/10.
 */
public class JavaThreadVMStackSOF {
    public static void main(String[] args) {

        int i = 1;
        try {
            while (true){
                new Thread(()->{
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
                i++;
                if(i==2000)
                    break;
            }
        }catch (Throwable e){
            System.out.println("总共启动线程："+i);
            throw e;
        }
    }
}
