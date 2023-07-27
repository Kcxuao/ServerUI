package com.kcxuao.serverUI.Utils;

import com.kcxuao.serverUI.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeSet;

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
    public String[] getPID(String name) throws IOException {
        String port = serverInfo.getMaps().get(name + ".port");
        Process exec = runtime.exec("lsof -i:" + port);

        BufferedReader br = exec.inputReader();
        TreeSet<String> pidSet = new TreeSet<>();

        String line;
        for (int i = 0; (line = br.readLine()) != null; i++) {
            if (i == 0) {
                continue;
            }
            pidSet.add(line.split(" +")[1]);
        }
        br.close();
        return pidSet.toArray(new String[]{});
    }

    /**
     * 根据PID结束程序
     * @param pids pid数组
     */
    public void stopServer(String[] pids) throws IOException {
        for (String pid : pids) {
            runtime.exec("kill -9 " + pid);
        }
    }

    public void startServer(String name) throws Exception {
        String command = serverInfo.getMaps().get(name + ".command");
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
