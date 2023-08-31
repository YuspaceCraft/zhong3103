package com.qf.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class GatewayInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object obj) throws Exception {
        String gateway = request.getHeader("gateway");
        // System.out.println(gateway);
        if (!"123456".equals(gateway)) {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("绕过网关，非法访问！");
            return false;
        }
        return true;
    }
}
