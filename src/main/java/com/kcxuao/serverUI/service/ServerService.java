package com.kcxuao.serverUI.service;

import com.kcxuao.serverUI.domain.MyProcess;
import java.io.IOException;

public interface ServerService {

    boolean getStatus(String name) throws IOException;

    void stop(String name) throws IOException;

    void start(String name) throws Exception;

    MyProcess getSystemStatus() throws IOException;
}
