package ru.korshun.wordscountanalyser;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class WordsAnalyzerTest {

  @Test
  void getWordsCount() {

    var testStr = "Hello, World! Hello, World1! Hello, World2!";
    var expectedMap = new HashMap<String, Integer>();
    expectedMap.put("hello", 3);
    expectedMap.put("world", 1);
    expectedMap.put("world1", 1);
    expectedMap.put("world2", 1);
    var result = WordsAnalyzer.getWordsCount(testStr);

    result.ifPresent(resultMap -> assertEquals(resultMap, expectedMap));


  }

}