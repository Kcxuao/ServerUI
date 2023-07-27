package com.kcxuao.serverUI.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeSet;

public class ServerUtils {


    private static final Runtime runtime = Runtime.getRuntime();

    /**
     * 根据端口号获取PID
     * @param port 端口号
     * @return PID字符串数组
     */
    public static String[] getPID(String port) throws IOException {
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
    public static void stopServer(String[] pids) throws IOException {
        for (String pid : pids) {
            runtime.exec("kill -9 " + pid);
        }
    }
}
