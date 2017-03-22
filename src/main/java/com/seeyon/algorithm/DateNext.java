package com.seeyon.algorithm;

/**
 * Created by yangyu on 16/12/22.
 */

import java.util.Date;

/**
 * 取任意一天的下一天
 * 原理：因为Date保存的时间其实是一个long型的整型数据，这个值是任何一个时间距离1970年1月1日，0时0分0秒的毫秒数
 * 所以直接加上1天的毫秒数就是下一天
 */
public class DateNext {
    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(getNextDate(now));
    }

    private static Date getNextDate(Date date){
        long millSecond = 1*24*60*60*1000;
        Date date1 = new Date(date.getTime()+millSecond);
        return date1;
    }
}
