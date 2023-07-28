package com.kcxuao.serverUI.controller;

import com.kcxuao.serverUI.domain.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${serviceUI.projectPath}")
    private String projectPath;

    @PostMapping
    public void upload(@RequestParam MultipartFile file, @RequestParam String dirName) throws IOException {
        String filename = file.getOriginalFilename();
        String[] split = Objects.requireNonNull(file.getContentType()).split("/");
        if (!"zip".equals(split[1])) {
            throw new CustomException("文件只能为zip");
        }

        String path = projectPath + dirName + "/" +filename;
        file.transferTo(new File(projectPath + dirName + "/" +filename));

        String command = "sh src/main/resources/shell/"+ dirName +".sh " + path;
        // 运行项目
        Runtime.getRuntime().exec(command);
    }
}
