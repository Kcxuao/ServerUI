package com.kcxuao.serverUI.controller;

import com.kcxuao.serverUI.Utils.JwtUtils;
import com.kcxuao.serverUI.Utils.LoginUtils;
import com.kcxuao.serverUI.common.R;
import com.kcxuao.serverUI.domain.CustomException;
import com.kcxuao.serverUI.domain.User;

import org.springframework.web.bind.annotation.*;
import java.util.Objects;


@RestController
@RequestMapping("/login")
public class LoginController {


    @PostMapping
    public R<String> login(@RequestBody User user) throws Exception {
        String encry = LoginUtils.md5Encry(user.getKey());
        user.setKey(encry);

        User u = (User) LoginUtils.readFile();
        if (!Objects.equals(u, user)) {
            throw new CustomException("登录失败");
        }

        String token = JwtUtils.createToken(user);
        return R.success(token);
    }
}