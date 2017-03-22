package com.seeyon.algorithm;

/**
 * Created by yangyu on 16/12/22.
 */

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {8, 7, 6, 5, 4, 3, 2};
        bubble(array);

    }

    private static void bubble(int[] src) {
        int tmp = 0;
        /**
         *每次将最大的数移动到数组末尾，N个长度的数组外层循环只需要N-1次，因为最后一个数一定是最小的不用再冒泡
         */
        for (int i = 0; i < src.length-1; i++) {
            /**
             * 内层循环次数为N-1-i次，因为已经放置数组末尾的数据不需要再参与比较
             * 冒泡排序的核心思想就是：每次将最大的数放置队列末尾，直到每个数据都被正确放置
             */
            for (int j = 0; j < src.length - i - 1; j++) {
                if (src[j] > src[j + 1]) {
                    tmp = src[j];
                    src[j] = src[j + 1];
                    src[j + 1] = tmp;
                }
            }
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
