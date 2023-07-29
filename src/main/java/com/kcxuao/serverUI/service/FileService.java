package com.kcxuao.serverUI.service;

import com.kcxuao.serverUI.domain.FileInfo;

import java.io.IOException;

public interface FileService {

    void upload(FileInfo fileInfo) throws IOException;
}
