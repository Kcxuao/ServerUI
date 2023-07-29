package com.kcxuao.serverUI.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 封装配置中的内容
 */
@Data
@Configuration
@ConfigurationProperties("serviceui")
public class ServerInfo {

    private String userPath;
    private Map<String, String> maps;

    public String getPort(String name) {
        String s = maps.get(name + ".port");
        if (s == null) throw new CustomException("服务不存在");
        return s;
    }

    public String getCommand(String name) {
        String s = maps.get(name + ".command");
        if (s == null) throw new CustomException("服务不存在");
        return s;
    }

}
