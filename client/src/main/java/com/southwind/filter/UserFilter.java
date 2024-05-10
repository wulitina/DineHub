package com.southwind.filter;

import com.southwind.entity.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 让没有登录的用户不能访问index.html页面，所以需要实现一个用户过滤器
// @Component注解交给ioc进行管理
// 设置拦截请求这里没有web.xml了，因为这是springboot项目,@WebFilter注解里添加需要拦截的信息,filterName = "userFilter"如果不加过滤器的名字，也会失效导致网页无法显示
@Component
@WebFilter(urlPatterns = {"/index.html"},filterName = "userFilter")
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null){
            //说明没有登录，需要响应到登录界面,重定向，此时没有视图解析器，不能用"login"表示，直接写资源名称就可以
            response.sendRedirect("login.html");
        }else {
            //登录之后放行就行
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
