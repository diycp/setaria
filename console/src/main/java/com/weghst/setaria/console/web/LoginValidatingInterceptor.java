package com.weghst.setaria.console.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录验证拦截器.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class LoginValidatingInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(Constants.SESSION_USER_ATTR_NAME) == null) {
            response.sendRedirect(request.getContextPath() + "/p/login.v");
            return false;
        }

        return true;
    }
}
