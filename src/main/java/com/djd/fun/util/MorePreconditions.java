//  Copyright (c) 2016 JGD Licensed under the MIT license
package com.djd.fun.util;

import com.google.common.base.Strings;
import com.sun.istack.internal.Nullable;

/**
 * Collections of static helper methods like {@link com.google.common.base.Preconditions}
 *
 * @author JGD
 * @since 8/7/16
 */
public class MorePreconditions {
  // Prevent instance creation.
  private MorePreconditions() {}

  /**
   * Check if the given numbers are positive integers e.g., +1, +2, +3 etc
   * @param numbers
   * @throws IllegalArgumentException if at least one number is not positive integer
   */
  public static void checkPositiveIntegers(int ... numbers) {
    for (int number : numbers) {
      if (number < 1) {
        throw new IllegalArgumentException(number + " is not positive integer.");
      }
    }
  }

  /**
   * Check if the given numbers are non negative integers e.g., 0, +1, +2, +3 etc
   * @param numbers
   * @throws IllegalArgumentException if at least one number is negative integer
   */
  public static void checkNonNegativeIntegers(int ... numbers) {
    for (int number : numbers) {
      if (number < 0) {
        throw new IllegalArgumentException(number + " is not positive integer.");
      }
    }
  }
}
