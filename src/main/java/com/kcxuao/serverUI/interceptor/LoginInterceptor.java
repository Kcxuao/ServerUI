package com.kcxuao.serverUI.interceptor;

import com.kcxuao.serverUI.Utils.JwtUtils;
import com.kcxuao.serverUI.domain.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("拦截成功 ==> {}", request.getRequestURI());

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");

        String url = request.getRequestURI();
        String method = request.getMethod();

        String[] filterPaths = {"OPTIONS", "login", "v2", "doc", "webjars", "ico", "swagger", "error"};
        boolean flag = check(filterPaths, url, method);

        if (flag) {
            return true;
        }

        String token = request.getHeader("token");
        if (token == null || !JwtUtils.verifyToken(token)) {
            throw new CustomException("token error");
        }

        return true;
    }

    public boolean check(String[] paths, String url, String method) {
        for (String path : paths) {
            if (url.contains(path) || method.contains(path)) {
                return true;
            }
        }
        return false;
    }
}