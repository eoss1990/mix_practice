package com.seeyon.array;

import com.seeyon.po.User;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by yangyu on 17/1/3.
 */

/**
 * CopyOnWriteArrayList使用的时候只是复制其内部的数组，但是不同组数内部的元素还是同一个引用对象
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        User user = new User("1","yangyu","18","chengdu");
        User user1 = new User("2","linling","18","chengdu");
        System.out.println(user);
        System.out.println(user1);

        CopyOnWriteArrayList<User> list = new CopyOnWriteArrayList<>();
        list.add(user);
        list.add(user1);

        for (User u : list){
            System.out.println(u);
        }
    }
}
