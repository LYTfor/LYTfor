package com.papercheck;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtils {
    public static String readFile(String filePath) throws IOException {
        try {
            return Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IOException("无法读取文件: " + filePath, e);
        }
    }

    public static void writeResult(String filePath, double similarity) throws IOException {
        String result = String.format("%.2f", similarity);
        try {
            Files.writeString(Paths.get(filePath), result, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new IOException("无法写入结果文件: " + filePath, e);
        }
    }
}