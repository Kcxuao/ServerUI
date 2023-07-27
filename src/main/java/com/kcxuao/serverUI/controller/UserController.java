package com.kcxuao.serverUI.controller;

import com.kcxuao.serverUI.common.R;
import com.kcxuao.serverUI.domain.User;
import com.kcxuao.serverUI.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/users")
public class UserController {


    @Resource
    private UserService userService;

    @PostMapping("/login")
    public R<String> login(@RequestBody User user) throws Exception {
        String token = userService.login(user);
        return R.success(token);
    }
}
