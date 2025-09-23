package com.papercheck;

import java.io.IOException;
import java.util.*;

public class PaperComparator {

    public static double calculateSimilarity(String originalPath, String plagiarizedPath) {
        try {
            String originalText = FileUtils.readFile(originalPath);
            String plagiarizedText = FileUtils.readFile(plagiarizedPath);

            // 处理文件读取失败的情况
            if (originalText == null || plagiarizedText == null) {
                return -1.0; // 返回-1表示文件读取失败
            }

            // 处理空文件
            if (originalText.isEmpty() || plagiarizedText.isEmpty()) {
                if (originalText.isEmpty() && plagiarizedText.isEmpty()) return 1.0;
                return 0.0;
            }

            // 计算文本相似度
            return calculateTextSimilarity(originalText, plagiarizedText);
        } catch (Exception e) {
            // 捕获所有未预期的异常
            ExceptionHandler.handleGeneralException(e);
            return -1.0; // 返回-1表示计算过程出现异常
        }
    }

    private static double calculateTextSimilarity(String text1, String text2) {
        try {
            // 基于字符的Jaccard相似度计算
            Set<Character> set1 = new HashSet<>();
            Set<Character> set2 = new HashSet<>();

            for (char c : text1.toCharArray()) {
                if (!Character.isWhitespace(c)) {
                    set1.add(c);
                }
            }

            for (char c : text2.toCharArray()) {
                if (!Character.isWhitespace(c)) {
                    set2.add(c);
                }
            }

            Set<Character> intersection = new HashSet<>(set1);
            intersection.retainAll(set2);

            Set<Character> union = new HashSet<>(set1);
            union.addAll(set2);

            if (union.isEmpty()) return 1.0;

            return (double) intersection.size() / union.size();
        } catch (Exception e) {
            // 捕获计算过程中的异常
            ExceptionHandler.handleGeneralException(e);
            return -1.0;
        }
    }
}