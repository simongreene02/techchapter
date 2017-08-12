package com.djd.fun.tachchapter.demo011reverse;

import javax.annotation.Nullable;

/**
 * Create a function which taks an array of chars which contains multiple words.
 * Reverse words in place.
 *
 * @author jr.ninja
 * @since 08/11/2017
 */
public class ReverseWords {

  public static void reverseWords(@Nullable char[] words) {
    if (words == null || words.length < 2 || hasOnlyOneWord(words)) {
      return;
    }
    assert(words.length > 1);

    // first reverse entire string
    reverseCharsInRange(words, 0, words.length - 1);

    // reverse each word (delimited by whitespace)
    int forwardIndex = 0;
    for (int i = 0; i < words.length; i++) {
      if (words[i] == ' ') {
        int reverseIndex = i - 1;
        reverseCharsInRange(words, forwardIndex, reverseIndex);
        forwardIndex = i + 1;
      }
    }
  }

  private static void reverseCharsInRange(char[] data, int forwardIndex, int reverseIndex) {
    while (forwardIndex < reverseIndex) {
      swapChatAt(data, forwardIndex++, reverseIndex--);
    }
  }

  /**
   * swap chars in data array at index1 and index2
   *
   * @param data
   * @param index1
   * @param index2
   */
  private static void swapChatAt(char[] data, int index1, int index2) {
    if (index1 < 0 || index2 < 0 || index1 == index2 || index1 >= data.length || index2 >= data.length) {
      throw new IllegalArgumentException("index should be in a correct range");
    }
    // swap with XOR requires no additinal space
    data[index1] ^= data[index2];
    data[index2] ^= data[index1];
    data[index1] ^= data[index2];
  }

  private static boolean hasOnlyOneWord(char[] words) {
    // check if there are more than one word
    for (int i = 0; i < words.length; i++) {
      if (words[i] == ' ') {
        return false;
      }
    }
    return true;
  }
}
