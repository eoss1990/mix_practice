package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyu on 16/11/10.
 */
public class RuntimeConstantPoolOOM {

//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        int i = 0;
//        while (true){
//            list.add(String.valueOf(i++).intern());
//        }
//    }

    public static void main(String[] args) {
        String st1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(st1.intern()==st1);

        String st2 = new StringBuilder("ja").append("va").toString();
        System.out.println(st2.intern()==st2);
    }
}
