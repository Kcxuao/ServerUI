package com.kcxuao.serverUI.service.impl;

import com.kcxuao.serverUI.domain.CustomException;
import com.kcxuao.serverUI.domain.FileInfo;
import com.kcxuao.serverUI.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {

    @Value("${serviceUI.projectPath}")
    private String projectPath;
    @Override
    public void upload(FileInfo fileInfo) throws IOException {
        MultipartFile file = fileInfo.getFile();
        String filename = file.getOriginalFilename();

        String[] split = Objects.requireNonNull(file.getContentType()).split("/");
        if (!"zip".equals(split[1])) {
            throw new CustomException("文件只能为zip");
        }

        String path = projectPath + fileInfo.getEnvName() + "/" +filename;
        file.transferTo(new File(path));

        String command = "sh ./shell/"+ fileInfo.getEnvName() +".sh " + path;
        // 运行项目
        Runtime.getRuntime().exec(command);
    }
}
