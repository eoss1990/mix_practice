package com.seeyon.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangyu on 17/1/3.
 */
public class TestConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("yangyu","nihao");
    }
}
