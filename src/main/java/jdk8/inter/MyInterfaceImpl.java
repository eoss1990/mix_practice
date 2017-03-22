package jdk8.inter;

/**
 * Created by yangyu on 2017/3/22.
 */
public class MyInterfaceImpl implements MyInterface {

    public static void main(String[] args) {
        System.out.println(MyInterface.say("yangyu"));

        System.out.println(new MyInterfaceImpl().speak("speak speak"));
    }
}
