package com.kcxuao.serverUI.controller;


import com.kcxuao.serverUI.Utils.ServerUtils;
import com.kcxuao.serverUI.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/server")
public class ServerController {

    @Autowired
    private ServerUtils serverUtils;

    @GetMapping("/{name}")
    public R<String[]> getStatus(@PathVariable String name) throws IOException {
        log.info("查询服务 ==> {}", name);
        String[] pid = serverUtils.getPID(name);
        return R.success(pid);
    }

    @DeleteMapping
    public R<String> stop(@RequestBody String[] pids) throws IOException {
        log.info("终止进程 ==> {}", Arrays.toString(pids));
        serverUtils.stopServer(pids);
        return R.success("OK");
    }

    @GetMapping("/start/{name}")
    public R<String> start(@PathVariable String name) throws Exception {
        serverUtils.startServer(name);
        return R.success("OK");
    }

}
