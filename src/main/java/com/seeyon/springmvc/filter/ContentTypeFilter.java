package com.seeyon.springmvc.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by yangyu on 16/11/8.
 */
public class ContentTypeFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    }

    @Override
    public void destroy() {

    }
}
