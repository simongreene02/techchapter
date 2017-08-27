package com.djd.fun.tachchapter.demo014swing.maze.shapes;

import java.awt.Polygon;

public class TriangleDown extends Polygon {

  private static final int SMALLER = 4;

  public TriangleDown(int row, int col, int size) {
    super(getXCoordinates(col, size), getYCoordinates(row, size), 3);
  }

  private static int[] getXCoordinates(int col, int size) {
    return new int[]{
        col * size + SMALLER,
        col * size + size - SMALLER,
        col * size + (size / 2)
    };
  }

  private static int[] getYCoordinates(int row, int size) {

    return new int[]{
        row * size + SMALLER,
        row * size + SMALLER,
        row * size + size - SMALLER,
    };
  }
}
