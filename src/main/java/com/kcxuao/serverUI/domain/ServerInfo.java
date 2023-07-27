package com.kcxuao.serverUI.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties("serviceui")
public class ServerInfo {

    private String userPath;
    private Map<String, String> maps;
}
