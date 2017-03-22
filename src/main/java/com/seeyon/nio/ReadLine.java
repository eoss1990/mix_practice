package com.seeyon.nio;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangyu on 2017/2/27.
 */
public class ReadLine {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("/Users/yangyu/Downloads/test")));
        String result;
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("ijb");
        Matcher mt;
        for (int i=1;(result=reader.readLine())!=null;i++){
            sb.append(result);
            mt = pattern.matcher(result);
            while (mt.find()){
                System.out.println("第"+i+"行："+mt.start());
            }
        }
    }
}
