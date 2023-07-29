package com.kcxuao.serverUI.controller;

import com.kcxuao.serverUI.common.R;
import com.kcxuao.serverUI.domain.User;

import com.kcxuao.serverUI.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Api(tags = "用户登录接口")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("登录")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "user", value = "用户信息", required = true)
    )
    @PostMapping
    public R<String> login(@RequestBody User user, HttpServletRequest request) throws Exception {
        log.info("用户登录 ==> {}", request.getRemoteHost());
        String token = loginService.login(user);
        return R.success(token);
    }
}