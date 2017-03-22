package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyu on 16/11/10.
 */
public class HeapOOM {
    public static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
