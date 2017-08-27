package com.djd.fun.tachchapter.demo014swing.maze.shapes;

import java.awt.Polygon;


public class Diamond extends Polygon {

  /**
   * @param row
   * @param col
   * @param size width or height
   */
  public Diamond(int row, int col, int size) {
    super(getXCoordinates(col, size), getYCoordinates(row, size), 4);
  }

  private static int[] getXCoordinates(int col, int size) {
    int delta = size / 2;
    return new int[]{
        col * size + delta,
        col * size + size,
        col * size + delta,
        col * size
    };
  }

  private static int[] getYCoordinates(int row, int size) {
    int delta = size / 2;
    return new int[]{
        row * size,
        row * size + delta,
        row * size + size,
        row * size + delta
    };
  }
}
