package com.seeyon.algorithm;

/**
 * Created by yangyu on 16/12/22.
 */

/**
 * 打印1-100中所有素数
 */
public class Prime {
    public static void main(String[] args) {
        for (int i = 1; i <=100; i++) {
            if (isPrime(i))
                System.out.println(i);
        }
    }

    private static boolean isPrime(int num) {
        if (num == 1)
            return false;
        int sprtNum = (int) Math.sqrt(num);
        for (int i = 2; i <= sprtNum ; i++) {
            if (num % i ==0)
                return false;
        }
        return true;
    }
}
