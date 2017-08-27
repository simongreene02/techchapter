//  Copyright (c) 2016 JGD Licensed under the MIT license
package com.djd.fun.tachchapter.demo014swing.maze;

import com.djd.fun.util.ExpectData;
import com.djd.fun.util.PrintStreamTestHelper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * @author JGD
 * @since 8/15/16
 */
public class FloorTest {
  private static final String[][] BIG_FLOOR_PLAN = {
      {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
      {"W", "W", "W", "W", "W", "H", "H", "H", "P", "H"},
      {"W", "W", "W", "W", "W", "H", "H", "H", "H", "H"},
      {"W", "W", "W", "W", "W", "H", "W", "W", "W", "W"},
      {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
      {"W", "W", "W", "W", "W", "H", "W", "W", "W", "W"},
      {"W", "H", "T", "H", "W", "H", "H", "W", "W", "W"},
      {"W", "W", "H", "W", "W", "W", "H", "H", "H", "H"},
      {"W", "W", "T", "W", "W", "W", "W", "W", "W", "H"},
      {"W", "W", "W", "W", "H", "H", "H", "H", "W", "H"},
      {"W", "W", "T", "H", "H", "T", "T", "H", "H", "H"},
      {"W", "W", "W", "W", "H", "H", "H", "H", "W", "H"},
      {"W", "W", "W", "W", "W", "W", "W", "W", "W", "H"},
      {"W", "W", "W", "W", "W", "H", "H", "H", "H", "H"},
      {"W", "W", "W", "W", "W", "H", "T", "H", "H", "H"},
      {"W", "H", "H", "H", "W", "H", "H", "H", "H", "H"},
      {"W", "H", "T", "H", "W", "W", "W", "W", "W", "H"},
      {"W", "H", "H", "H", "H", "H", "H", "H", "H", "H"}};
  private static final String[][] FLOOR_PLAN = {
      {"H", "P"},
      {"W", "W"},
      {"W", "H"}};

  private static final String[][] THREE_BY_THREE_HOLLOW = {
      {"H", "H", "H"},
      {"H", "P", "H"},
      {"H", "H", "H"}};
  private static final Location PLAYER_LOCATION = Location.of(1, 1);
  private PrintStreamTestHelper helper;
  private Floor floor;
  private Floor floor3x3;

  @Before
  public void setUp() {
    floor = new Floor(FLOOR_PLAN);
    floor3x3 = new Floor(THREE_BY_THREE_HOLLOW);
    helper = PrintStreamTestHelper.init();
  }

  @Test
  public void getNorthLocation_noMove() {
    Location current = Location.of(0, 0);
    assertSame(current, floor3x3.getNorthLocation(current));
  }

  @Test
  public void getEastLocation_noMove() {
    Location current = Location.of(2, 2);
    assertSame(current, floor3x3.getEastLocation(current));
  }

  @Test
  public void getSouthLocation_noMove() {
    Location current = Location.of(2, 2);
    assertSame(current, floor3x3.getSouthLocation(current));
  }

  @Test
  public void getWestLocation_noMove() {
    Location current = Location.of(0, 0);
    assertSame(current, floor3x3.getWestLocation(current));
  }

  @Test
  public void getNorthLocation_moved() {
    assertEquals(Location.of(0, 1), floor.getNorthLocation(PLAYER_LOCATION));
  }

  @Test
  public void getEastLocation_moved() {
    assertEquals(Location.of(1, 2), floor3x3.getEastLocation(PLAYER_LOCATION));
  }

  @Test
  public void getSouthLocation_moved() {
    assertEquals(Location.of(2, 1), floor3x3.getSouthLocation(PLAYER_LOCATION));
  }

  @Test
  public void getWestLocation_moved() {
    assertEquals(Location.of(1, 0), floor3x3.getWestLocation(PLAYER_LOCATION));
  }


  @Test
  public void isValidIndeces_valid() {
    for (int row = 0; row < FLOOR_PLAN.length; row++) {
      for (int col = 0; col < FLOOR_PLAN[row].length; col++) {
        assertTrue(floor.isValidLocation(Location.of(row, col)));
      }
    }
  }

  @Test
  public void isValidIndeces_invalidRowIndex() {
    assertFalse(floor.isValidLocation(Location.of(FLOOR_PLAN.length, 0)));
  }

  @Test
  public void isValidIndeces_invalidColIndex() {
    assertFalse(floor.isValidLocation(Location.of(0, FLOOR_PLAN[0].length)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void isValidIndeces_negativeIndex() {
    assertFalse(floor.isValidLocation(Location.of(-1, -1)));
  }

  @Test
  public void printBoard() {
    Floor floor = new Floor(BIG_FLOOR_PLAN);
    floor.printBoard(helper.newPrintStream());
    helper.assertStringEquals(ExpectData.toString("floor_output.txt"));
  }
}