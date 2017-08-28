package com.djd.fun.tachchapter.demo014swing.maze.shapes;

import java.awt.Polygon;

import com.djd.fun.util.MorePreconditions;

public class Diamond extends Polygon {

  private Diamond(Builder builder) {
    super(builder.xCoordinates, builder.yCoordinates, 4);
  }

  public static Diamond with(int row, int col, int size) {
    return new Builder().row(row).col(col).size(size).build();
  }

  private static class Builder {
    private int row;
    private int col;
    private int size;
    private int[] xCoordinates;
    private int[] yCoordinates;

    public Builder row(int row) {
      this.row = row;
      return this;
    }

    public Builder col(int col) {
      this.col = col;
      return this;
    }

    public Builder size(int size) {
      this.size = size;
      return this;
    }

    private int[] getXCoordinates() {
      int delta = size / 2;
      return new int[]{
          col * size + delta,
          col * size + size,
          col * size + delta,
          col * size
      };
    }

    private int[] getYCoordinates() {
      int delta = size / 2;
      return new int[]{
          row * size,
          row * size + delta,
          row * size + size,
          row * size + delta
      };
    }

    public Diamond build() {
      MorePreconditions.checkPositiveIntegers(size);
      MorePreconditions.checkNonNegativeIntegers(row, col);
      xCoordinates = getXCoordinates();
      yCoordinates = getYCoordinates();
      return new Diamond(this);
    }
  }
}
