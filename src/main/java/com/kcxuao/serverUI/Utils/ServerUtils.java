package com.kcxuao.serverUI.Utils;

import com.kcxuao.serverUI.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeSet;

/**
 * 服务工具类
 */
@Component
public class ServerUtils {


    private static final Runtime runtime = Runtime.getRuntime();

    @Autowired
    private ServerInfo serverInfo;

    /**
     * 根据端口号获取PID
     * @param name 端口号
     * @return PID字符串数组
     */

    /**
     * 通过服务名查询PID判断服务是否在线
     * @param name 服务名
     * @return boolean
     * @throws IOException
     */
    public boolean status(String name) throws IOException {
        String[] pid = getPID(name);
        return pid.length != 0;
    }


    /**
     * 通过服务名查询PID
     * @param name 服务名
     * @return String[PID]
     * @throws IOException
     */
    private String[] getPID(String name) throws IOException {
        String port = serverInfo.getPort(name);
        Process exec = runtime.exec("lsof -i:" + port);

        BufferedReader br = exec.inputReader();

        // PID去重
        TreeSet<String> pidSet = new TreeSet<>();

        String line;
        for (int i = 0; (line = br.readLine()) != null; i++) {
            if (i == 0) {
                continue;
            }
            System.out.println(line);
            pidSet.add(line.split(" +")[1]);
        }
        br.close();
        return pidSet.toArray(new String[]{});
    }

    /**
     * 根据PID结束程序
     * @param name 服务名称
     */
    public void stopServer(String name) throws IOException {
        String[] pids = getPID(name);
        for (String pid : pids) {
            runtime.exec("kill -9 " + pid);
        }
    }

    /**
     * 启动服务
     * @param name 服务名称
     */
    public void startServer(String name) throws Exception {
        String command = serverInfo.getCommand(name);
        Process exec = runtime.exec(command);
        exec.waitFor();

        BufferedReader br = exec.inputReader();
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}
