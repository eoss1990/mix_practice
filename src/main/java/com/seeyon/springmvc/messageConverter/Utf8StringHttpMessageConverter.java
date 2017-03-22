package com.seeyon.springmvc.messageConverter;

import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;

/**
 * Created by yangyu on 16/11/9.
 */
public class Utf8StringHttpMessageConverter extends StringHttpMessageConverter {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public Utf8StringHttpMessageConverter(){
        super(DEFAULT_CHARSET);
    }
}
