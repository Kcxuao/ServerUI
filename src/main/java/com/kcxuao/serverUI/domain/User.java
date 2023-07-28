package com.kcxuao.serverUI.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户登录实体类")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("登录密钥")
    private String key;
}
