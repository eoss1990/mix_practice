package com.seeyon.cxf.rest;

import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;
import com.seeyon.mybatis.pojo.People;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyu on 2017/2/17.
 */
public class Client {

    public static void main(String[] args) throws ClassNotFoundException {
        String address = "http://localhost:8080/ws/rest";
        List providerList = new ArrayList();
        providerList.add(new FastJsonProvider());

        //for get
//        List produstList = WebClient.create(address,providerList).path("products").
//                accept(MediaType.APPLICATION_JSON).get(List.class);

        //for post
        People people = new People("1001", "yangyu", "chengdu", "18088888", "man");
        List list = WebClient.create(address, providerList).path("update")
                .type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .post(people, List.class);
    }

    @Test
    public void testHttps() {
        String address = "https://raw.github.com/square/okhttp/master/README.md";
        String resp = WebClient.create(address).accept(MediaType.TEXT_PLAIN).get(String.class);
        System.out.println(resp);
    }
}
