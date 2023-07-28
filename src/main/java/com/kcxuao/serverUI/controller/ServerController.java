package com.kcxuao.serverUI.controller;


import com.kcxuao.serverUI.Utils.ServerUtils;
import com.kcxuao.serverUI.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/server")
public class ServerController {

    @Autowired
    private ServerUtils serverUtils;

    @GetMapping("/{name}")
    public R<Boolean> getStatus(@PathVariable String name) throws IOException {
        log.info("查询服务 ==> {}", name);
        boolean status = serverUtils.status(name);
        return R.success(status);
    }

    @DeleteMapping("/{name}")
    public R<String> stop(@PathVariable String name) throws IOException {
        log.info("终止进程 ==> {}", name);
        serverUtils.stopServer(name);
        return R.success("OK");
    }

    @PostMapping
    public R<String> start(@RequestBody String name) throws Exception {
        log.info("启动进程 ==> {}", name);
        serverUtils.startServer(name);
        return R.success("OK");
    }

}
