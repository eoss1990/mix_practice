package com.seeyon.springmvc;

import org.springframework.web.servlet.RequestToViewNameTranslator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangyu on 16/11/2.
 */
public class MyRequestToViewNameTranslator implements RequestToViewNameTranslator {
    @Override
    public String getViewName(HttpServletRequest request) throws Exception {
        return "go";
    }
}
