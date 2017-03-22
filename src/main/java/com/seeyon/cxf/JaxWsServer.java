package com.seeyon.cxf;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * Created by yangyu on 2017/2/16.
 */
public class JaxWsServer {
    public static void main(String[] args) {
        /**
         * 使用JaxWsServerFactoryBean，可以反复多次发布Ws
         */
        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
        factoryBean.setAddress("http://localhost:8080/ws/soap/hello");
        factoryBean.setServiceClass(HelloService.class);
        factoryBean.setServiceBean(new HelloServiceImpl());
        Server server = factoryBean.create();

        factoryBean.setAddress("http://localhost:8080/ws/soap/hello1");
        factoryBean.setServiceClass(HelloService.class);
        factoryBean.setServiceBean(new HelloServiceImpl());
        Server server1 = factoryBean.create();

        factoryBean.setAddress("http://localhost:8080/ws/soap/hello2");
        factoryBean.setServiceClass(HelloService.class);
        factoryBean.setServiceBean(new HelloServiceImpl());
        Server server2 = factoryBean.create();

        server1.destroy();

        System.out.println("发布完成");
    }
}
