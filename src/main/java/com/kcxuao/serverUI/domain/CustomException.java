package com.kcxuao.serverUI.domain;

/**
 * 自定义错误类
 */
public class CustomException extends RuntimeException {

    public CustomException(String msg) {
        super(msg);
    }
}
