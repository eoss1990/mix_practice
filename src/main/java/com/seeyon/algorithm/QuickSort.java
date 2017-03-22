package com.seeyon.algorithm;

/**
 * Created by yangyu on 16/12/23.
 */

/**
 * 快速排序
 * 核心原理：将头元素作为比较元素，小数值放左边，大数值放右边，然后递归2分，直到分不下去了就完成了排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = {5,9,8,4,7,3,6,2};
        print(array);
        sort(array,0,array.length-1);
        print(array);

    }

    private static void print(int[] before) {
        for (int i = 0; i < before.length; i++) {
            System.out.print(before[i] + " ");
        }
        System.out.println();
    }

    private static void sort(int[] array,int low,int high){
        if (low>=high)
            return;
        if ((high-low) == 1){
            if (array[0]>array[1])
                swap(array,0,1);
            return;
        }
        int pivot = array[low];
        int left = low+1;
        int right = high;
        while (left < right){
            while (left<right&&left<=high){
                if (array[left]>pivot)
                    break;
                left++;
            }
            while (left<=right&&right>low){
                if (array[right]<pivot)
                    break;
                right--;
            }
            if (left<right)
                swap(array,left,right);
        }
        swap(array,low,right);
        sort(array,low,right);
        sort(array,right+1,high);

    }

    private static void swap(int[] array,int i,int j){
        int tmp;
        tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
