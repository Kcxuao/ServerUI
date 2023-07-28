package com.kcxuao.serverUI.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.*;

@Component
public class LoginUtils {

    public static String userPath;

    public static String md5Encry(String str) {

        byte[] bytes = DigestUtils.md5Digest(str.getBytes());
        return encry(bytes);
    }

    private static String encry(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= 10;
        }
        return new String(bytes);
    }

    public static Object readFile() throws Exception {

        File file = new File(userPath);
        if (!file.exists() || !file.isFile()) {
            throw new Exception(userPath + " 文件不存在");
        }

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        return ois.readObject();
    }


    @Value("${serviceUI.userPath}")
    private void setUserPath(String path) {
        userPath = path;
    }
}
