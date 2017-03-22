package com.seeyon.SpringBean.customerEvent;

import org.springframework.context.ApplicationEvent;

/**
 * Created by yangyu on 16/10/12.
 */
public class CustomerEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public CustomerEvent(Object source) {
        super(source);
    }
}
