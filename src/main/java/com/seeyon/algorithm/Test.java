package com.seeyon.algorithm;

import java.util.Date;

/**
 * Created by yangyu on 16/12/29.
 */
public class Test {

    private int[] array = {8,4,2,5,9,55,4,12,65,1,43,6,42,7,89};

    private int len = array.length;
    /**
     * 冒泡排序
     */
    @org.junit.Test
    public void bubbleSort(){
        int tmp;
        for (int i = 0; i < len-1; i++) {

            for (int j=0;j<len-i-1;j++){
                if (array[j]>array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1]=tmp;
                }
            }

        }
        print(array);
    }

    /**
     * 插入排序
     */
    @org.junit.Test
    public void insertSort(){
        int tmp;
        int j;
        for (int i = 1; i < len; i++) {
            tmp = array[i];
            for (j = i;j>0;j--){
                if (array[j-1]>tmp)
                    array[j]=array[j-1];
                else
                    break;
            }
            array[j]=tmp;
        }
        print(array);
    }

    /**
     * 获取今天的下一天时间
     */
    @org.junit.Test
    public void dateNext(){
        Date now = new Date();
        long millTime = 1*24*60*60*1000;
        Date next = new Date(now.getTime()+millTime);
        System.out.println(next);
    }

    /**
     * 打印99乘法表
     * i控制行数，j控制每行的乘数
     */
    @org.junit.Test
    public void multiplication99(){
        for (int i=1,j=1;i<=9;j++){
            System.out.print(i+"*"+j+"="+i*j+" ");
            if (i==j){
                i++;
                j=0;
                System.out.println();
            }
        }
    }

    /**
     * 打印1-1000的回文数字
     */
    @org.junit.Test
    public void Palindrome(){
        int num;
        for (int i = 10; i < 1000; i++) {
            num = i;
            int tmp=0;
            while (num>0){
                tmp = tmp*10+num%10;
                num/=10;
            }
            if (tmp == i)
                System.out.println(i);
        }
    }

    /**
     * 打印1-100的素数
     */
    @org.junit.Test
    public void prime(){
        int sqrt;
        boolean flag = true;
        for (int i = 2; i < 100; i++) {
            sqrt = (int) Math.sqrt(i);
            for (int j=2;j<=sqrt;j++){
                if (i % j==0){
                    flag=false;
                    break;
                }
            }
            if (flag)
                System.out.println(i);
            flag=true;
        }
    }



    public void print(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
}
