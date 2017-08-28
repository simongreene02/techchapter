package com.djd.fun.tachchapter.demo014swing.maze.shapes;

import com.google.common.testing.EqualsTester;

import org.junit.Test;

public class StarPolygonTest {

  @Test
  public void equality() {
    new EqualsTester()
        .addEqualityGroup(Diamond.with(0, 0, 20), Diamond.with(0, 0, 20))
        .addEqualityGroup(Diamond.with(1, 0, 20))
        .addEqualityGroup(Diamond.with(0, 1, 20))
        .addEqualityGroup(Diamond.with(0, 0, 80))
        .testEquals();
  }

}