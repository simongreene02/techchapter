//  Copyright (c) 2016 JGD Licensed under the MIT license
package com.djd.fun.util;

import com.google.common.base.Strings;
import com.sun.istack.internal.Nullable;

/**
 * Collection of static methods for string manipulations
 *
 * @author JGD
 * @since 8/20/16
 */
public class MoreStrings {

  /**
   * returns firstLetter character of the given string.
   * e.g.,
   * <pre><code>
   * input="abcd"; output="a";
   * input="a"; output="a";
   * input=""; output=null;
   * input=null; output=null;
   * </code></pre>
   *
   * @param input string value
   * @return firstLetter character of the given string. {@code null} if input is either null or empty
   */
  public static String firstLetter(@Nullable String input) {
    return (Strings.isNullOrEmpty(input)) ? null : input.substring(0, 1);
  }

  /**
   * returns n-1 length string
   * e.g.,
   * <pre><code>
   * input="abcd"; output="bcd";
   * input=null; output=null;
   * input=""; output=null;
   * input="a"; output=null;
   * </code></pre>
   */
  public static String secondToLast(@Nullable String input) {
    return (input != null && input.length() > 1) ? input.substring(1) : null;
  }
}
