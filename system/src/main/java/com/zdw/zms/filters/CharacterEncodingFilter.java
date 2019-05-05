package com.zdw.zms.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CharacterEncodingFilter
 * @Description TODO
 * @Author zoudingwei
 * @Date 2019/5/4 10:44 AM
 * @Version 1.0
 **/
@WebFilter(urlPatterns = "/*",filterName = "CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter {


    /*
     * @Author zoudingwei
     * @Description 必须要重写init()和destroy()方法，否则启动报错
     * @Date 2019/5/4 11:00 AM
     * @Param [filterConfig]
     * @return void
     **/
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response  =(HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        filterChain.doFilter(request,response);
    }
}
