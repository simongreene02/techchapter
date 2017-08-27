//  Copyright (c) 2016 JGD Licensed under the MIT license
package com.djd.fun.util;

import java.io.IOException;
import java.net.URL;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.Resources;

/**
 * Helper to load a text file from resources dir and convert that to String.
 * This is useful in junit test to compare the actual result against expected result.
 *
 * @author JGD
 * @since 8/24/16
 */
public class ExpectData {

  /**
   * @param resourceName file path under resources dir
   * @return string represents content of the file
   */
  public static String toString(String resourceName) {
    URL url = Resources.getResource(resourceName);
    try {
      return Resources.toString(url, Charsets.UTF_8);
    } catch (IOException e) {
      throw Throwables.propagate(e);
    }
  }
}
