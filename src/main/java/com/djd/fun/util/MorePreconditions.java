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

  public static String checkNeitherNullOrEmpty(@Nullable String value) {
    if (Strings.isNullOrEmpty(value)) {
      throw new IllegalArgumentException("The value is either null or empty.");
    }
    return value;
  }

  /**
   * Check if the specified number is a whole number >= 0
   *
   * @param number
   * @return number
   * @throws IllegalArgumentException if number is not a whole number
   */
  public static int checkWholeNumber(int number) {
    if (number < 0) {
      throw new IllegalArgumentException(number + " is not a whole number.");
    }
    return number;
  }

  /**
   * Check if the number is counting number >= 1
   *
   * @param number
   * @return number
   * @throws IllegalArgumentException if number is not a counting number
   */
  public static int checkCountingNumber(int number) {
    if (number < 1) {
      throw new IllegalArgumentException(number + " is not a counting number.");
    }
    return number;
  }
}
