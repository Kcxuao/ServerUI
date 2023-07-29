package com.kcxuao.serverUI.controller;

import com.kcxuao.serverUI.common.R;
import com.kcxuao.serverUI.domain.FileInfo;
import com.kcxuao.serverUI.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Slf4j
@Api(tags = "文件上传与自动部署接口")
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    @ApiOperation("上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileInfo", value = "文件信息", required = true, paramType = "body"),
    })
    public R<String> upload(FileInfo fileInfo) throws IOException {
        log.info("上传文件 ==> {}", fileInfo.getFile().getOriginalFilename());
        fileService.upload(fileInfo);
        return R.success("文件部署成功");
    }
}
