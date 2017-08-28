package com.djd.fun.tachchapter.demo014swing.maze.shapes;

import java.awt.Polygon;

import com.djd.fun.util.MorePreconditions;

public class Triangle extends Polygon {

  enum Direction {
    UP, DOWN, LEFT, RIGHT
  }

  private Triangle(Builder builder) {
    super(builder.xCoordinates, builder.yCoordinates, 3);
  }

  public static Triangle down(int row, int col, int size) {
    return new Builder().row(row).col(col).size(size).direction(Direction.DOWN).build();
  }

  public static Triangle up(int row, int col, int size) {
    return new Builder().row(row).col(col).size(size).direction(Direction.UP).build();
  }

  private static class Builder {
    private static final int DEFAULT_SMALLER = 4;
    private int row;
    private int col;
    private int size;
    private int smaller = DEFAULT_SMALLER;
    private int[] xCoordinates;
    private int[] yCoordinates;
    private Direction direction = Direction.UP;

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

    public Builder smaller(int smaller) {
      this.smaller = smaller;
      return this;
    }

    public Builder direction(Direction direction) {
      this.direction = direction;
      return this;
    }

    private int[] getXCoordinatesUp() {
      int delta = size / 2;
      return new int[]{
          col * size + delta,
          col * size + smaller,
          col * size + size - smaller
      };
    }

    private int[] getYCoordinatesUp() {
      int delta = size / 2;
      return new int[]{
          row * size + smaller,
          row * size + size - smaller,
          row * size + size - smaller
      };
    }

    private int[] getXCoordinatesDown() {
      return new int[]{
          col * size + smaller,
          col * size + size - smaller,
          col * size + (size / 2)
      };
    }

    private int[] getYCoordinatesDown() {
      return new int[]{
          row * size + smaller,
          row * size + smaller,
          row * size + size - smaller,
      };
    }

    private Triangle build() {
      MorePreconditions.checkPositiveIntegers(size);
      MorePreconditions.checkNonNegativeIntegers(row, col, smaller);
      switch (direction) {
        case UP:
          xCoordinates = getXCoordinatesUp();
          yCoordinates = getYCoordinatesUp();
          break;
        case DOWN:
          xCoordinates = getXCoordinatesDown();
          yCoordinates = getYCoordinatesDown();
          break;
        case LEFT:
        case RIGHT:
        default:
          throw new UnsupportedOperationException("These are not yet supported");
      }
      return new Triangle(this);
    }
  }
}
