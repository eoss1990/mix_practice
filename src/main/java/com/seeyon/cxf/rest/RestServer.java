package com.seeyon.cxf.rest;

import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyu on 2017/2/17.
 */
public class RestServer {

    public static void main(String[] args) {
        List<Class<?>> resourceClassList = new ArrayList();
        resourceClassList.add(Service.class);

        List<ResourceProvider> resourceProviderList = new ArrayList<ResourceProvider>();
        resourceProviderList.add(new SingletonResourceProvider(new Service()));

        List<Object> providerList = new ArrayList<>();
        providerList.add(new FastJsonProvider());


        //发布服务
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setAddress("http://localhost:8080/ws/rest");
        factory.setResourceClasses(resourceClassList);
        factory.setResourceProviders(resourceProviderList);
        factory.setProviders(providerList);
        Server server = factory.create();

        System.out.println("rest is published");
    }
}
