package com.kcxuao.serverUI.controller;

import com.kcxuao.serverUI.common.R;
import com.kcxuao.serverUI.domain.CustomException;


import com.kcxuao.serverUI.domain.FileInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j

@Api(tags = "文件上传与自动部署接口")
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${serviceUI.projectPath}")
    private String projectPath;

    @PostMapping
    @ApiOperation("上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileInfo", value = "文件信息", required = true, paramType = "body"),
    })
    public R<String> upload(FileInfo fileInfo) throws IOException {
        MultipartFile file = fileInfo.getFile();
        String filename = file.getOriginalFilename();

        log.info("上传文件 ==> {}", filename);

        String[] split = Objects.requireNonNull(file.getContentType()).split("/");
        if (!"zip".equals(split[1])) {
            throw new CustomException("文件只能为zip");
        }

        String path = projectPath + fileInfo.getEnvName() + "/" +filename;
        file.transferTo(new File(path));

        String command = "sh ./shell/"+ fileInfo.getEnvName() +".sh " + path;
        // 运行项目
        Runtime.getRuntime().exec(command);
        return R.success("文件上传并运行成功");
    }
}
