package top.wangjingxin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 17854 on 2016/11/27.
 */
@WebFilter(urlPatterns = "/*")
public class Filter implements javax.servlet.Filter
{
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        if(servletRequest.getRequestURI().equals("/")||servletRequest.getSession().getAttribute("user_id")!=null||servletRequest.getRequestURI().matches("(.*\\.(html|htm|gif|jpg|jpeg|bmp|png|ico|txt|js|css|woff)$)|(.*(login)$)"))chain.doFilter(request,response);
    }
    public void destroy()
    {

    }
}
