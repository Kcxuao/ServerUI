package com.kcxuao.serverUI.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.*;

@Component
public class LoginUtils {

    public static String userPath;

    public String md5Encry(String str) {

        byte[] bytes = DigestUtils.md5Digest(str.getBytes());
        return encry(bytes);
    }

    private String encry(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= 10;
        }
        return new String(bytes);
    }

    public Object readFile() throws Exception {

        ClassPathResource file = new ClassPathResource("users");

        if (!file.exists()) {
            throw new Exception(file + " 文件不存在");
        }

        ObjectInputStream ois = new ObjectInputStream(file.getInputStream());
        return ois.readObject();
    }

    @Value("${serviceUI.userPath}")
    private void setUserPath(String path) {
        userPath = path;
    }
}
