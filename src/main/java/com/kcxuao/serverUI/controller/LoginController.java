package com.kcxuao.serverUI.controller;

import com.kcxuao.serverUI.Utils.JwtUtils;
import com.kcxuao.serverUI.Utils.LoginUtils;
import com.kcxuao.serverUI.common.R;
import com.kcxuao.serverUI.domain.CustomException;
import com.kcxuao.serverUI.domain.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;


@Api(tags = "用户登录接口")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginUtils loginUtils;

    @ApiOperation("登录")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "user", value = "用户信息", required = true)
    )
    @PostMapping
    public R<String> login(@RequestBody User user) throws Exception {
        String encry = loginUtils.md5Encry(user.getKey());
        user.setKey(encry);

        User u = (User) loginUtils.readFile();
        if (!Objects.equals(u, user)) {
            throw new CustomException("登录失败");
        }

        String token = JwtUtils.createToken(user);
        return R.success(token);
    }
}