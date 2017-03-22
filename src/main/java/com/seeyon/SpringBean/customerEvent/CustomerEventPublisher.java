package com.seeyon.SpringBean.customerEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * Created by yangyu on 16/10/12.
 */
public class CustomerEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(){
        CustomerEvent ce = new CustomerEvent(this);
        applicationEventPublisher.publishEvent(ce);
    }
}
