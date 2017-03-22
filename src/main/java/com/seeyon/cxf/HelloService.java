package com.seeyon.cxf;

import javax.jws.WebService;

/**
 * Created by yangyu on 2017/2/16.
 */
@WebService
public interface HelloService {

    String say(String name);
}
