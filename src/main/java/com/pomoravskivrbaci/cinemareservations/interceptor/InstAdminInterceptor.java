package com.pomoravskivrbaci.cinemareservations.interceptor;

import com.pomoravskivrbaci.cinemareservations.model.User;
import com.pomoravskivrbaci.cinemareservations.model.UserRole;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InstAdminInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURI();
        User loggedUser = (User)request.getSession().getAttribute("loggedUser");
        if(requestUrl.contains("inst_admin")) {
            if(loggedUser == null || !loggedUser.getRole().equals(UserRole.INSTADMIN)) {
                response.setStatus(401);
                return false;
            }
        }
        return true;
    }
}
