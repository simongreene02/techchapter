//  Copyright (c) 2016 JGD Licensed under the MIT license
package com.djd.fun.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author JGD
 * @since 8/20/16
 */
public class MoreStringsTest {
  @Test
  public void firstLetter_ABC_A() {
    assertEquals("A", MoreStrings.firstLetter("ABC"));
  }

  @Test
  public void firstLetter_A_A() {
    assertEquals("A", MoreStrings.firstLetter("A"));
  }

  @Test
  public void firstLetter_empty_null() throws Exception {
    assertNull(MoreStrings.firstLetter(""));
  }

  @Test
  public void firstLetter_null_null() throws Exception {
    assertNull(MoreStrings.firstLetter(null));
  }

  @Test
  public void secondToLast_empty_null() {
    assertNull(MoreStrings.secondToLast(""));
  }

  @Test
  public void secondToLast_null_null() {
    assertNull(MoreStrings.secondToLast(null));
  }

  @Test
  public void secondToLast_ABC_BC() {
    assertEquals("BC", MoreStrings.secondToLast("ABC"));
  }

  @Test
  public void secondToLast_B_null() {
    assertNull(MoreStrings.secondToLast("B"));
  }
}