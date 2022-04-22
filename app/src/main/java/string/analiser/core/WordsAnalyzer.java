package string.analiser.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Map.Entry;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Сlass for frequency analysis of text.
 * @author Dmitriy Fomenko.
 */
public class WordsAnalyzer {

    private Map<String, Integer> frequencyMap;

    public WordsAnalyzer(String string) {
        this.frequencyMap = calcFrequency(string);
    }

    public WordsAnalyzer(Path path) throws IOException {
        this.frequencyMap = calcFrequency(path);
    }

    public WordsAnalyzer(File file) throws IOException {
        this.frequencyMap = calcFrequency(file);
    }

    /**
     * Сalculate frequency of words in file.
     * @param path - path to file.
     * @return Map<String, Integer> - map of words and their frequency.
     */
    private Map<String, Integer> calcFrequency(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path, UTF_8)) {
            Map<String, Integer> map = lines.flatMap(value -> Arrays.stream(value.trim().split("[,.!?:;\\s+]+")))
                    .map(String::toLowerCase)
                    .collect(toMap(identity(), it -> 1, Integer::sum));
            return sort(map);
        }
    }

    /**
     * Сalculate frequency of words in text.
     * @param string - path to file.
     * @return Map<String, Integer> - map of words and their frequency.
     */
    private Map<String, Integer> calcFrequency(String string) {
        Map<String, Integer> map = Arrays.stream(string.trim().split("[,.!?:;\\s+]+"))
                .map(String::toLowerCase)
                .collect(toMap(identity(), it -> 1, Integer::sum));
        return sort(map);
    }

    /**
     * Calculate frequency of words in file.
     * @param file - file to analyze.
     * @return Map<String, Integer> - map of words and their frequency.
     */
    private Map<String, Integer> calcFrequency(File file) {
        return calcFrequency(file.getPath());
    }

    /**
     * Sorting HashMap<String, Integer> by value.
     * @param map - map to sort.
     * @return sorted map.
     */
    private Map<String, Integer> sort(Map<String, Integer> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * Get map of words and their frequency.
     * @return Map<String, Integer> - map of words and their frequency.
     */
    public Map<String, Integer> getFrequencyMap() {
        return frequencyMap;
    }
}
