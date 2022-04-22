package string.analiser.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("MagicNumber")
class WordsAnalyzerTest {

    @Test
    @DisplayName("Count words in string")
    void getWordsCount() {

        var testStr = "Hello, World! Hello, World1! Hello, World2!";
        var expectedMap = new HashMap<String, Integer>();

        expectedMap.put("hello", 3);
        expectedMap.put("world", 1);
        expectedMap.put("world1", 1);
        expectedMap.put("world2", 1);

        WordsAnalyzer wordsAnalyzer = new WordsAnalyzer(testStr);
        Map<String, Integer> wordsCount = wordsAnalyzer.getFrequencyMap();

        assertEquals(wordsCount, expectedMap);
    }

    @Test
    @DisplayName("Count words in file")
    void getWordsCountFromFile() throws IOException {
        WordsAnalyzer wordsAnalyzer = new WordsAnalyzer(Path.of("src/test/resources/text"));
        assertNotNull(wordsAnalyzer.getFrequencyMap());
    }
}
