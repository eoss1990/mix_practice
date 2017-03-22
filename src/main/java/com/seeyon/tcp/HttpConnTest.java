package com.seeyon.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyu on 16/12/21.
 */

/**
 * 网络爬虫，抓取互联网中的页面
 */
public class HttpConnTest {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://robbinfan.com/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        Map<String,List<String>> header = connection.getHeaderFields();
        for (String key : header.keySet()){
            System.out.println(key+":"+header.get(key));
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
        String str = null;
        while ((str = br.readLine())!=null){
            System.out.println(str);
        }
        connection.disconnect();
    }
}
