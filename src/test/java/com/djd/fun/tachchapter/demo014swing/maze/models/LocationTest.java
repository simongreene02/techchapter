//  Copyright (c) 2016 JGD Licensed under the MIT license
package com.djd.fun.tachchapter.demo014swing.maze.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author JGD
 * @since 9/11/16
 */
public class LocationTest {

  @Test
  public void equality() {
    assertEquals(Location.of(1, 2), Location.of(1, 2));
    assertEquals(Location.of(1, 2).hashCode(), Location.of(1, 2).hashCode());
    assertNotEquals(Location.of(2, 1), Location.of(1, 2));
    assertNotEquals(Location.of(2, 1).hashCode(), Location.of(1, 2).hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRow() {
    Location.of(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidCol() {
    Location.of(0, -1);
  }

}
