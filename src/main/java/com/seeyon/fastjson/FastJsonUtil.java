package com.seeyon.fastjson;

import com.alibaba.fastjson.JSON;
import com.seeyon.dom4j.Dom4jTest;
import com.seeyon.mybatis.pojo.People;

/**
 * Created by yangyu on 16/10/29.
 */
public class FastJsonUtil {
    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new People("a","b","c","d","e")));

        String json = "{\"address\":\"c\",\"id\":\"a\",\"name\":\"b\",\"sex\":\"e\",\"tel\":\"d\"}";
        People p = JSON.parseObject(json,People.class);
        System.out.println(p.getId());
    }
}
