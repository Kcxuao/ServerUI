package com.kcxuao.serverUI.hanlder;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.kcxuao.serverUI.common.R;
import com.kcxuao.serverUI.domain.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.NestedServletException;

import java.io.IOException;

/**
 * 全局错误拦截
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * token解析错误
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {JWTDecodeException.class, IllegalArgumentException.class, SignatureVerificationException.class, NestedServletException.class})
    public R<String> JWTDecodeExceptionHandler(Exception ex) {

        log.error("token解析异常 ==> {}", ex.getMessage());
        return R.error("TOKEN无效");
    }


    /**
     * 自定义业务异常
     * @param ex
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = {CustomException.class})
    public  R<String> CustomExceptionHandler(Exception ex) {

        log.error("业务异常 ==> {}", ex.getMessage());
        return R.error(ex.getMessage());
    }
}