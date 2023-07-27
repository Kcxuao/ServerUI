package com.kcxuao.serverUI.interceptor;

import com.alibaba.fastjson.JSON;
import com.kcxuao.serverUI.Utils.JwtUtils;
import com.kcxuao.serverUI.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截成功 ==> {}", request.getRequestURI());

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Allow-Methods","*");

        String url = request.getRequestURI();
        String method = request.getMethod();

        boolean flag =  "OPTION".equals(method)
                || url.contains("login");

        if (flag) {
            return true;
        }

        String token = request.getHeader("token");

        if (token == null || !JwtUtils.verifyToken(token)) {
            response.getWriter().write(JSON.toJSONString(R.error("token异常")));
            return false;
        }

        return true;
    }
}