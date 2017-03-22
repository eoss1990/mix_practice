package com.seeyon.SpringBean.customerEvent;

import org.springframework.context.ApplicationListener;

/**
 * Created by yangyu on 16/10/12.
 */
public class CustomerEventListener implements ApplicationListener<CustomerEvent> {
    @Override
    public void onApplicationEvent(CustomerEvent event) {
        System.out.println("this is CustomerEvent is comming!");
    }
}
