package com.seeyon.algorithm;

/**
 * Created by yangyu on 16/12/22.
 */

/**
 * 插入排序代码实现
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {3, 5, 4, 1, 8, 11, 9};
        doInsertSort(arr);
    }

    private static void doInsertSort(int[] src) {
        int len = src.length;
        /**
         * 插入排序的核心思想就是：将第一个数据看成是一个新的数组，从第二个数据开始逐步插入新数组当中，直到最后一个数据插入完毕
         * 将数据插入新数组时，将数据作为tmp，从新数组的最后一个开始向前比较（因为新数组已经是有序的了），只要比tmp大就右移一位
         */
        for (int i = 1; i < len; i++) {
            int j;
            int tmp = src[i];
            for (j = i; j > 0; j--) {
                if (src[j - 1] > tmp)
                    src[j] = src[j - 1];
                else
                    break;
            }
            src[j] = tmp;
        }
        print(src);
    }

    private static void print(int[] before) {
        for (int i = 0; i < before.length; i++) {
            System.out.print(before[i] + " ");
        }
        System.out.println();
    }
}
