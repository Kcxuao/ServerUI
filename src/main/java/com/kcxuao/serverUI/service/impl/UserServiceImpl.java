package com.kcxuao.serverUI.service.impl;

import com.kcxuao.serverUI.Utils.JwtUtils;
import com.kcxuao.serverUI.Utils.LoginUtils;
import com.kcxuao.serverUI.domain.User;
import com.kcxuao.serverUI.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {

    @Override
    public String login(User user) throws Exception {
        String encry = LoginUtils.md5Encry(user.getPassword());
        user.setPassword(encry);

        User o = (User) LoginUtils.readFile();
        if (!Objects.equals(o, user)) {
            throw new Exception("登录失败");
        }

        return JwtUtils.createToken(user);
    }
}
