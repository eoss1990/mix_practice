package com.seeyon.algorithm;

/**
 * Created by yangyu on 16/12/22.
 */

/**
 * 打印99乘法表
 */
public class Multiplication99 {
    public static void main(String[] args) {
        for (int i=1,j=1;j<=9;i++){
            System.out.print(j+"*"+i+"="+j*i+" ");
            if (i==j){
                j++;
                i=0;
                System.out.println();
            }
        }
    }
}
