package com.kcxuao.serverUI.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@ApiModel("文件上传实体类")
public class FileInfo {

    @ApiModelProperty("项目环境名")
    private String envName;

    @ApiModelProperty("文件")
    private MultipartFile file;
}
