package com.seeyon.string;

/**
 * Created by yangyu on 2017/3/3.
 * 根据字节截取字符串，遇到中文要截取完整
 */
public class SubString {

    public static void main(String[] args) {
        String s = "我ABC仲";
        System.out.println(subString(s,7));
    }

    public static String subString(String s,int len) {
        int a = 0;
        StringBuilder tmp = new StringBuilder();
        for(int i=0;i<s.length();i++) {
            byte[] b = String.valueOf(s.charAt(i)).getBytes();
            a+=b.length;
            tmp.append(s.charAt(i));
            if(a>len) {
                break;
            }

        }
        return tmp.toString();
    }
}
