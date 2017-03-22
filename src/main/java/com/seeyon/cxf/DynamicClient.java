package com.seeyon.cxf;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;

/**
 * Created by yangyu on 2017/2/17.
 */
public class DynamicClient {

    public static void main(String[] args) {
        DynamicClientFactory factory = DynamicClientFactory.newInstance();
        Client client = factory.createClient("http://localhost:8080/ws/soap/hello?wsdl");

        try {
            Object[] objects = client.invoke("say","yangyu");
            System.out.println(objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
