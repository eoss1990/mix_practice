package com.seeyon.string;

import org.junit.Test;

/**
 * Created by yangyu on 2017/3/9.
 */
public class Format {

    public static void main(String[] args) {
        String sql = "select * from %d";
        long abc = 1111;
        String table = "test";
        System.out.println(String.format(sql,abc));
    }
}
