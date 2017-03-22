package com.seeyon.jsptag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by yangyu on 2017/2/16.
 */
public class JspTag extends TagSupport{

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int doStartTag() throws JspException {
        System.out.println("tag start "+getName());
        return 1;
    }

}
