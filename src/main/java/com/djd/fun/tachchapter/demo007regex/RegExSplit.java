package com.djd.fun.tachchapter.demo007regex;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

/**
 * Count unique words
 *
 * @author JGD
 * @since 9/20/16
 */
public class RegExSplit {
  private final Map<String, AtomicInteger> wordCounts;

  public RegExSplit() {
    wordCounts = Maps.newHashMap();
  }

  public Map<String, AtomicInteger> countWords(String sentences) {
    if (Strings.isNullOrEmpty(sentences)) {
      return ImmutableMap.of();
    }
    String[] words = sentences.split("[\\.\\s',:]");
    Stream.of(words)
        .filter(word -> !word.isEmpty())
        .map(String::toLowerCase)
        .forEach(this::count);

    return wordCounts;
  }

  private void count(String word) {
    AtomicInteger count = wordCounts.get(word);
    if (count == null) {
      count = new AtomicInteger();
      wordCounts.put(word, count);
    }
    count.incrementAndGet();
  }
}
