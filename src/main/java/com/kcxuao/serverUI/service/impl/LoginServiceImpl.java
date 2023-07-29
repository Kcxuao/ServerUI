package com.kcxuao.serverUI.service.impl;

import com.kcxuao.serverUI.Utils.JwtUtils;
import com.kcxuao.serverUI.Utils.LoginUtils;
import com.kcxuao.serverUI.domain.CustomException;
import com.kcxuao.serverUI.domain.User;
import com.kcxuao.serverUI.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginUtils loginUtils;

    @Override
    public String login(User user) throws Exception {
        String encry = loginUtils.md5Encry(user.getKey());
        user.setKey(encry);

        User u = (User) loginUtils.readFile();
        if (!Objects.equals(u, user)) {
            throw new CustomException("登录失败");
        }

        return JwtUtils.createToken(user);
    }
}
