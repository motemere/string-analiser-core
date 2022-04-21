package ru.korshun.stringanalizer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class counts words in input text file.
 *
 * @author k0rshun
 */
public final class WordsAnalyzer {

  private WordsAnalyzer() { }

  /**
   * Function counts words in file and return a map in which the keys are
   * words and values is count of this word in the file.
   *
   * @param file file for counting
   * @return Optional with map with words count or Optional.empty() if
   * we get an exception
   */
  public static Optional<HashMap<String, Integer>> getWordsCount(
      final String file) {
    var result = new HashMap<String, Integer>();
    var path = Paths.get(file);

    try (var lineStream = Files.newBufferedReader(path,
        StandardCharsets.UTF_8).lines()) {
      lineStream
          .filter(line -> !line.trim().isEmpty())
          .forEach(line -> lineProcess(result, line));
      return Optional.of(sortMap(result));
    } catch (IOException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }

  /**
   * Function gets the line, split it by space and check every word contains
   * in the map.
   *
   * @param map map with result data.
   * @param line line from file.
   */
  private static void lineProcess(final HashMap<String, Integer> map,
                                  final String line) {
    Arrays.stream(line.split(" "))
        .forEach(word -> {
          var changedWord =
              word
                  .trim()
                  .toLowerCase(Locale.getDefault())
                  .replaceAll("([^\\p{L}\\d]+)'*\\1*", "");

          if (changedWord.isEmpty()) return;

          map.put(changedWord, map.get(changedWord) != null
              ? map.get(changedWord) + 1 : 1);
        });
  }

  /**
   * Function sorted map by key.
   *
   * @param map map with result data.
   * @return sorted map by key (desc)
   */
  private static HashMap<String, Integer> sortMap(
      final HashMap<String, Integer> map) {
    return map.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
  }

}
