package com.hmdp.utils;

import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author IvanSnow
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Get session
        HttpSession session = request.getSession();
        //Get user from session
        Object user = session.getAttribute("user");
        //if user exists
        if (user == null) {
            //not exists, reject, return 401
            response.setStatus(401);
            return false;
        }
        //save user in ThreadLocal
        UserHolder.saveUser((UserDTO) user);

        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
