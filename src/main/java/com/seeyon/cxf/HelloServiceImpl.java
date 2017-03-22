package com.seeyon.cxf;

import javax.jws.WebService;

/**
 * Created by yangyu on 2017/2/16.
 */
@WebService
public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String name) {
        return "hello"+name;
    }
}
