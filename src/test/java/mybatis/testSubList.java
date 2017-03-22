package mybatis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyu on 16/10/18.
 */
public class testSubList {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");

        List list = arrayList.subList(0,2);
        ArrayList<String> subList = new ArrayList<String>(list);
        list.clear();

        System.out.println(arrayList.size());
        System.out.println(subList.size());
    }
}
