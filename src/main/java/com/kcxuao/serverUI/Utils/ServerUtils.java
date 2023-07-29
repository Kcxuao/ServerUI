package com.kcxuao.serverUI.Utils;

import com.kcxuao.serverUI.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
     * 通过服务名查询PID
     *
     * @param name 服务名
     * @return String[PID]
     */
    public String[] getPID(String name) throws IOException {

        String port;
        try {
            port = serverInfo.getPort(name);
        } catch (Exception e) {
            port = name;
        }

        Process exec = exec("lsof -i:" + port);
        List<String> read = read(exec);

        ArrayList<String> pids = new ArrayList<>();
        for (int i = 1; i < read.size(); i++) {
            String[] split = read.get(i).split(" +");
            pids.add(split[1]);
        }

        return pids.toArray(new String[0]);
    }



    public List<String> read(Process exec) throws IOException {
        BufferedReader br = exec.inputReader();

        TreeSet<String> set = new TreeSet<>();

        String line;
        while ((line = br.readLine()) != null) {
            set.add(line);
        }
        br.close();
        return set.stream().toList();
    }

    public Process exec(String command) throws IOException {
        return runtime.exec(
                new String[]{"sh", "-c", command}
        );
    }

    public static void main(String[] args) throws IOException {
        String[] pid = new ServerUtils().getPID("22");
        System.out.println(Arrays.toString(pid));
    }
}
