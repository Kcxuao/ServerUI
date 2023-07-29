package com.kcxuao.serverUI.service.impl;

import com.kcxuao.serverUI.Utils.ServerUtils;
import com.kcxuao.serverUI.domain.CustomException;
import com.kcxuao.serverUI.domain.MyProcess;
import com.kcxuao.serverUI.domain.ServerInfo;
import com.kcxuao.serverUI.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServerServiceImpl implements ServerService {

    @Autowired
    private ServerUtils serverUtils;

    @Autowired
    private ServerInfo serverInfo;

    @Override
    public boolean getStatus(String name) throws IOException {
        String[] pid = serverUtils.getPID(name);
        return pid.length != 0;
    }

    @Override
    public void stop(String name) throws IOException {
        String[] pids = serverUtils.getPID(name);
        for (String pid : pids) {
            serverUtils.exec("kill -9 " + pid);
        }
    }

    @Override
    public void start(String name) throws Exception {
        String command = serverInfo.getCommand(name);
        Process exec = serverUtils.exec(command);

        exec.waitFor();

        List<String> read = serverUtils.read(exec);
        System.out.println(read);
    }

    @Override
    public MyProcess getSystemStatus() throws IOException {
        Process exex1 = serverUtils.exec("ps -aux | sort -k4nr | head -5 | awk '{print $2,$3,$4,$11}'");
        Process exec2 = serverUtils.exec("vmstat -S m |sed -n '3p' |  awk '{print $4,$5,$6,$15}'");

        List<String> read1 = serverUtils.read(exex1);
        List<String> read2 = serverUtils.read(exec2);

        read2 = List.of(read2.get(0).split(" "));

        if (read1.isEmpty() || read2.isEmpty()) {
            throw new CustomException("系统状态查询错误");
        }

        MyProcess myProcess = new MyProcess();
        ArrayList<MyProcess.Process> list = new ArrayList<>();

        for (String s : read1) {
            MyProcess.Process process = new MyProcess.Process(s);
            list.add(process);
        }

        myProcess.setProcess(list);
        myProcess.setFreeMemory(read2.stream()
                .map(Integer::valueOf)
                .reduce(0, Integer::sum));
        myProcess.setFreeCpu(Double.valueOf(read2.get(3)));
        return myProcess;
    }
}
