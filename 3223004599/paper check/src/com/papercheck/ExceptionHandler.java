package com.papercheck;
import java.io.IOException;
public class ExceptionHandler {
    public static void handleIOException(IOException e) {
        System.err.println("文件操作错误: " + e.getMessage());
    }

    public static void handleGeneralException(Exception e) {
        System.err.println("程序执行错误: " + e.getMessage());
    }
}