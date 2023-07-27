package com.kcxuao.serverUI.controller;


import com.kcxuao.serverUI.Utils.ServerUtils;
import com.kcxuao.serverUI.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/servers")
public class ServerController {


    @GetMapping("/{port}")
    public R<String[]> getStatus(@PathVariable String port) throws IOException {
        log.info("查询端口号 ==> {}", port);
        String[] pid = ServerUtils.getPID(port);
        return R.success(pid);
    }

    @DeleteMapping
    public R<String> stop(@RequestBody String[] pids) throws IOException {
        log.info("终止进程 ==> {}", Arrays.toString(pids));
        ServerUtils.stopServer(pids);
        return R.success("OK");
    }

}
