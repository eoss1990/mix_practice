package com.seeyon.algorithm;

/**
 * Created by yangyu on 16/12/22.
 */

/**
 * 打印1-1000中的回文数字
 */
public class Palindrome {
    public static void main(String[] args) {
        for (int i = 10; i < 1000; i++) {
            if (isPalindrome(i)) {
                System.out.println(i);
            }
        }
    }

    private static boolean isPalindrome(int num) {
        int oldValue = num, tmp = 0;
        while (num > 0) {
            tmp = tmp * 10 + num % 10;
            num /= 10;
        }
        if (tmp == oldValue)
            return true;
        return false;
    }
}
