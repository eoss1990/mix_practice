package com.seeyon.string;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by yangyu on 16/12/26.
 */

/**
 * String性能研究
 * 1、使用StringTokenizer性能要优于String.split();
 * 2、indexOf+substring性能最好，但代码可读性最差
 */
public class StringTest {
    public static void main(String[] args) {
        String str = "aaa;bbb;ccc;ddd";
        StringTokenizer stringTokenizer = new StringTokenizer(str,";");
        while (stringTokenizer.hasMoreTokens()){
            System.out.println(stringTokenizer.nextToken());
        }
    }

    @Test
    public void testCharAt(){
        String str = "hello world";
        System.out.println(str.charAt(0));
    }

    @Test
    public void testLinkedList(){
        List list = new LinkedList();
        list.add("yangyu");
        list.add("linling");
        list.add("yangming");

        for (Iterator iterator = list.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }
}
