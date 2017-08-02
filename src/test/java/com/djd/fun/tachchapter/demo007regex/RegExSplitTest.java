package com.djd.fun.tachchapter.demo007regex;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.ImmutableSet;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author JGD
 * @since 9/20/16
 */
public class RegExSplitTest {
  private RegExSplit regExSplit;

  @Before
  public void setUp() {
    regExSplit = new RegExSplit();
  }

  @Test
  public void countWords() {
    regExSplit.countWords("After beating the eggs, Dana read the next step:");
    Map<String, AtomicInteger> counts = regExSplit.countWords("Add milk and eggs, then add flour and sugar.");
    assertThat(counts.keySet()).containsExactlyElementsIn(
        ImmutableSet.of("after", "beating", "the", "eggs", "dana", "read", "next", "step",
            "add", "milk", "and", "then", "flour", "sugar"));
    assertThat(counts.values().stream().filter(count -> count.get() == 1).count()).isEqualTo(10);
    assertThat(counts.values().stream().filter(count -> count.get() == 2).count()).isEqualTo(4);
  }
}