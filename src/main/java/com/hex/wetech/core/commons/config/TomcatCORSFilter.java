package com.hex.wetech.core.commons.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * TomcatCORSFilter
 *
 * @author Guofeng Lin
 * @since 2023/11/17
 */
public class TomcatCORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String originHeader = ((HttpServletRequest) servletRequest).getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", originHeader);
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
//x-requested-with,Authorization,Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE，DNT,X-Mx-ReqToken,Keep-Alive,User-Agent，If-Modified-Since,Cache-Control,SessionToken，accept"
        response.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
