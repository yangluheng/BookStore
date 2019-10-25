package com.xy.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class MyEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse= (HttpServletResponse) response;
        httpServletResponse.setHeader("content-type","text/html;charset=UTF-8");
        /*
        //1.设置POST请求中文乱码的问题
        HttpServletRequest hsr = (HttpServletRequest)request;
        if(hsr.getMethod().equalsIgnoreCase("post")){
*/
            request.setCharacterEncoding("UTF-8");
            //放行请求
            chain.doFilter(request, response);
            response.setCharacterEncoding("UTF-8");
        }


    }



