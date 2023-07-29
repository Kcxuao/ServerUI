package com.kcxuao.serverUI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyProcess {


    private Double freeCpu;

    private Integer freeMemory;

    private ArrayList<Process> process;

    @Data
    @AllArgsConstructor
    public static class Process {
        private Integer pid;
        private Double cpu;
        private Double mem;

        private String server;

        public Process(String s) {
            String[] split = s.split(" ");
            this.pid = Integer.valueOf(split[0]);
            this.cpu = Double.valueOf(split[1]);
            this.mem = Double.valueOf(split[2]);

            if (split[3].contains("/")) {
                String[] arr = split[3].split("/");
                this.server = arr[arr.length - 1];
            }
        }
    }
}
