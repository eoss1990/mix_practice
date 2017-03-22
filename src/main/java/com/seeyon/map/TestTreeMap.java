package com.seeyon.map;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangyu on 16/12/27.
 */
public class TestTreeMap {

    private static class Compare implements Comparator<MapBean>{

        @Override
        public int compare(MapBean o1, MapBean o2) {
            if (o1.num>o2.num)
                return 1;
            else if (o1.num<o2.num)
                return -1;
            return 0;
        }
    }

    public static void main(String[] args) {
        TreeMap map = new TreeMap(new Compare());
        MapBean mapBean1 = new MapBean(1);
        MapBean mapBean2 = new MapBean(2);
        MapBean mapBean3 = new MapBean(3);
        MapBean mapBean4 = new MapBean(4);
        map.put(mapBean1,"1");
        map.put(mapBean2,"2");
        map.put(mapBean3,"3");
        map.put(mapBean4,"4");

        Map map1 = map.subMap(mapBean2,mapBean4);
        for (Iterator iterator = map1.keySet().iterator();iterator.hasNext();){
            System.out.println(map1.get(iterator.next()));
        }
    }

    @Test
    public void testMap(){
        HashMap map = new HashMap();
        ConcurrentHashMap conMap = new ConcurrentHashMap();

        map.put(null,null);
        /**
         * ConcurrentHashMap中key与value均不可以为null
         */
        //conMap.put("yangyu",null);
        System.out.println(111);
        
    }
}
