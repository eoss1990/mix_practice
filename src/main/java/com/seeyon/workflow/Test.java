package com.seeyon.workflow;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * Created by yangyu on 16/11/3.
 */
public class Test {
    public static void main(String[] args) {
        PathMatcher pathMatcher = new AntPathMatcher();
        boolean flag = pathMatcher.match("/*","/index");
        System.out.println(flag);
    }

    @org.junit.Test
    public void test1(){
        System.out.println(111);
    }
}
