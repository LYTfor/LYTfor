package com.papercheck;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java -jar main.jar [original_file] [plagiarized_file] [answer_file]");
            System.exit(1);
        }

        String originalFilePath = args[0];
        String plagiarizedFilePath = args[1];
        String answerFilePath = args[2];

        try {
            double similarity = PaperComparator.calculateSimilarity(originalFilePath, plagiarizedFilePath);
            FileUtils.writeResult(answerFilePath, similarity);
            System.out.println("查重完成，重复率: " + String.format("%.2f", similarity));

        } catch (IOException e) {
            ExceptionHandler.handleIOException(e);
            System.exit(1);
        } catch (Exception e) {
            ExceptionHandler.handleGeneralException(e);
            System.exit(1);
        }
        try {
            System.out.println("程序将在60秒后退出，请用VisualVM连接分析...");
            Thread.sleep(60000);
            System.out.println("程序退出");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}