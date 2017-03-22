package com.seeyon.proxy;

import com.seeyon.proxy.aspectJ.Tag;
import org.springframework.stereotype.Component;

/**
 * Created by yangyu on 16/10/19.
 */
@Component
public class Bean implements BeanInterFace{

    public void say(){
        System.out.println("this is my self");
    }

    @Tag
    public void goodMorning(){
        System.out.println("good morning");
    }

    public void goodNight(){
        System.out.println("good night");
    }
}
