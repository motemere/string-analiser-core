package ru.korshun.stringanalizer;

/**
 * Main class.
 *
 * @author k0rshun
 */
public final class Main {

  private Main() { }

  /**
   * Main method.
   *
   * @param args input params
   */
  public static void main(final String[] args) {
    var result = WordsAnalyzer.getWordsCount("text");
    result.ifPresent(map -> map.forEach(
        (key, value) -> System.out.printf("%s: %d%n", key, value)));
  }
}
