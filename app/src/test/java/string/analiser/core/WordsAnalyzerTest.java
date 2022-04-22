package string.analiser.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SuppressWarnings("MagicNumber")
class WordsAnalyzerTest {

  private WordsAnalyzer wordsAnalyzer;

  @BeforeEach
  void init() {
    wordsAnalyzer = new WordsAnalyzer();
  }

  @Test
  @DisplayName("Count words in string")
  void getWordsCount() {

    var testStr = "Hello, World! Hello, World1! Hello, World2!";
    var expectedMap = new HashMap<String, Integer>();

    expectedMap.put("hello", 3);
    expectedMap.put("world", 1);
    expectedMap.put("world1", 1);
    expectedMap.put("world2", 1);

    final Optional<HashMap<String, Integer>> wordsCount = wordsAnalyzer.getWordsCount(testStr);

    wordsCount.ifPresent(resultMap -> assertEquals(resultMap, expectedMap));
  }

  @Test
  @DisplayName("Count words in file")
  void getWordsCountFromFile() throws IOException {
    assertNotNull(wordsAnalyzer
        .getWordsCount(Path.of("src/test/resources/text"))
    );
  }
}
