package com.djd.fun.tachchapter.demo014swing.maze.shapes;

import java.awt.Polygon;

public class Triangle extends Polygon {

  private static final int SMALLER = 4;

  public Triangle(int row, int col, int size) {
    super(getXCoordinates(col, size), getYCoordinates(row, size), 3);
  }

  private static int[] getXCoordinates(int col, int size) {
    int delta = size / 2;
    return new int[]{
        col * size + delta,
        col * size + SMALLER,
        col * size + size - SMALLER
    };
  }

  private static int[] getYCoordinates(int row, int size) {
    int delta = size / 2;
    return new int[]{
        row * size + SMALLER,
        row * size + size - SMALLER,
        row * size + size - SMALLER
    };
  }
}
