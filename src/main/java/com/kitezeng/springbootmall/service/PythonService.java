package com.kitezeng.springbootmall.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface PythonService {
    public abstract String usePython() ;
    public abstract String getFilesPath(String fileName,String fileName1);
    public abstract byte[] file2Byte(File file);
    public abstract byte[]  getBytesByStream(InputStream inputStream);
}

