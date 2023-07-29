package com.kcxuao.serverUI.controller;


import com.kcxuao.serverUI.Utils.ServerUtils;
import com.kcxuao.serverUI.common.R;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@Api(tags = "服务接口")
@RestController
@RequestMapping("/server")
public class ServerController {

    @Autowired
    private ServerUtils serverUtils;

    @ApiOperation(value = "查询服务状态", notes = "通过服务名查询服务是否在线")
    @GetMapping("/{name}")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "name", value = "服务名称", required = true, paramType = "path")
    )
    public R<Boolean> getStatus(@PathVariable String name) throws IOException {
        log.info("查询服务 ==> {}", name);
        boolean status = serverUtils.status(name);
        return R.success(status);
    }

    @ApiOperation(value = "终止服务", notes = "通过服务名终止服务")
    @DeleteMapping("/{name}")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "name", value = "服务名称", required = true, paramType = "path")
    )
    public R<String> stop(@PathVariable String name) throws IOException {
        log.info("终止进程 ==> {}", name);
        serverUtils.stopServer(name);
        return R.success("OK");
    }

    @ApiOperation(value = "启动服务", notes = "通过服务名启动服务")
    @GetMapping
    @ApiImplicitParams(
            @ApiImplicitParam(name = "name", value = "服务名", required = true)
    )
    public R<String> start(@RequestParam String name) throws Exception {
        log.info("启动进程 ==> {}", name);
        serverUtils.startServer(name);
        return R.success("OK");
    }

}
