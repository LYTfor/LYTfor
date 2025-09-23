package com.papercheck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;

/** 论文查重工具的单元测试类.*/

public class PaperComparatorTest {

  private static final double DELTA = 0.01;
  private static final double LOW_SIMILARITY_THRESHOLD = 0.3;
  private static final double HIGH_SIMILARITY_THRESHOLD = 0.5;

  /* 测试完全相同文本的相似度计算。*/

  @Test
    public void testIdenticalTexts() throws IOException {
    Files.write(Paths.get("test1_orig.txt"), "今天天气很好".getBytes());
    Files.write(Paths.get("test1_plagiarized.txt"), "今天天气很好".getBytes());

    double result = PaperComparator.calculateSimilarity(
                "test1_orig.txt", "test1_plagiarized.txt");
    assertEquals(1.0, result, DELTA);
  }

  /* 测试完全不同文本的相似度计算。*/

  @Test
    public void testCompletelyDifferent() throws IOException {
    Files.write(Paths.get("test2_orig.txt"), "今天天气很好".getBytes());
    Files.write(Paths.get("test2_plagiarized.txt"), "明天可能会下雨".getBytes());

    double result = PaperComparator.calculateSimilarity(
                "test2_orig.txt", "test2_plagiarized.txt");
    assertTrue(result < LOW_SIMILARITY_THRESHOLD);
  }

  /* 测试两个空文件的相似度计算。*/

  @Test
    public void testEmptyFiles() throws IOException {
    Files.write(Paths.get("test3_orig.txt"), "".getBytes());
    Files.write(Paths.get("test3_plagiarized.txt"), "".getBytes());

    double result = PaperComparator.calculateSimilarity(
                "test3_orig.txt", "test3_plagiarized.txt");
    assertEquals(1.0, result, DELTA);
  }

  /* 测试一个空文件的相似度计算。*/

  @Test
    public void testOneEmptyFile() throws IOException {
    Files.write(Paths.get("test4_orig.txt"), "今天天气很好".getBytes());
    Files.write(Paths.get("test4_plagiarized.txt"), "".getBytes());

    double result = PaperComparator.calculateSimilarity(
                "test4_orig.txt", "test4_plagiarized.txt");
    assertEquals(0.0, result, DELTA);
  }

  /* 测试相似文本的相似度计算。*/

  @Test
    public void testSimilarTexts() throws IOException {
    Files.write(Paths.get("test5_orig.txt"),
                "今天天气晴朗，适合外出散步".getBytes());
    Files.write(Paths.get("test5_plagiarized.txt"),
                "今天天气很好，适合出去走走".getBytes());

    double result = PaperComparator.calculateSimilarity(
                "test5_orig.txt", "test5_plagiarized.txt");
    assertTrue(result > LOW_SIMILARITY_THRESHOLD && result < 1.0);
  }
}
