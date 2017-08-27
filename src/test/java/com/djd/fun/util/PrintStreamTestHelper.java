//  Copyright (c) 2016 JGD Licensed under the MIT license
package com.djd.fun.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 *
 * Usage:
 * <pre><code>
 * PrintStreamTestHelper helper = PrintStreamTestHelper.init();
 * new YourPrinter(helper.newPrintStream()).print("ABC"); // just as an example
 * helper.assertStringEquals("ABC");
 * </code></pre>
 * @author JGD
 * @since 8/24/16
 */
public class PrintStreamTestHelper {
  private final ByteArrayOutputStream out;

  private PrintStreamTestHelper() {
    out = new ByteArrayOutputStream();
  }

  public PrintStream newPrintStream() {
    return new PrintStream(out);
  }

  public static PrintStreamTestHelper init() {
    return new PrintStreamTestHelper();
  }

  public void assertStringEquals(String expected) {
    assertEquals(expected, toString());
  }

  @Override
  public String toString() {
    return out.toString();
  }
}
