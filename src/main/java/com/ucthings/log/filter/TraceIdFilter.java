package com.ucthings.log.filter;

import com.fasterxml.uuid.Generators;
import com.ucthings.log.utils.TraceIdThreadLocal;
import javax.servlet.*;
import java.io.IOException;

/**
 * @desc 拦截web请求,增加TraceId
 * @author Aldrich Eugene
 * @since 2022-11-14
 */
public class TraceIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        TraceIdThreadLocal.add(Generators.timeBasedGenerator().generate().toString());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        TraceIdThreadLocal.remove();
    }
}
