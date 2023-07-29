package com.kcxuao.serverUI.Utils;

import com.kcxuao.serverUI.domain.CustomException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.*;

/**
 * 登录工具类
 */
@Component
public class LoginUtils {

    /**
     * md5加密
     * @param str 字符串
     * @return 加密字符串
     */
    public String md5Encry(String str) {

        byte[] bytes = DigestUtils.md5Digest(str.getBytes());
        return encry(bytes);
    }

    /**
     * 通过异或加密/解密
     * @param bytes 字节数组
     * @return 加密/解密字符串
     */
    private String encry(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= 10;
        }
        return new String(bytes);
    }

    /**
     * 读取用户文件内容
     * @return
     * @throws Exception
     */
    public Object readFile() throws Exception {

        ClassPathResource file = new ClassPathResource("users");

        if (!file.exists()) {
            throw new CustomException(file + " 文件不存在");
        }

        ObjectInputStream ois = new ObjectInputStream(file.getInputStream());
        return ois.readObject();
    }
}
