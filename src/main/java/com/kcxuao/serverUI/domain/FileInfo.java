package com.kcxuao.serverUI.domain;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileInfo {

    private String serverName;
    private String projectName;
    private MultipartFile[] files;
}
