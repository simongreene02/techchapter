package com.djd.fun.tachchapter.demo014swing.maze.shapes;

import com.djd.fun.util.MorePreconditions;

/**
 * This class was created based on the implementations found in http://java-sl.com/shapes.html
 */
public class StarPolygon extends BasePolygon {

  public StarPolygon(Builder builder) {
    super(builder.xCoordinates, builder.yCoordinates, builder.vertexCount * 2);
  }

  public static StarPolygon with(int row, int col, int size) {
    return new Builder(size).row(row).col(col).r(size/2).innerR(size/4).build();
  }

  private static class Builder {
    private final int delta;
    private final int size;
    private int x;
    private int y;
    private int r;
    private int innerR;
    private int vertexCount = 7;
    private double startAngle = 0.0;
    private int[] xCoordinates;
    private int[] yCoordinates;
    private Triangle.Direction direction = Triangle.Direction.UP;
    public Builder(int size) {
      MorePreconditions.checkPositiveIntegers(size);
      this.size = size;
      this.delta = size / 2;
    }

    public Builder r(int r) {
      this.r = r;
      return this;
    }

    public Builder innerR(int innerR) {
      this.innerR = innerR;
      return this;
    }

    public Builder vertexCount(int vertexCount) {
      this.vertexCount = vertexCount;
      return this;
    }

    public Builder startAngle(double startAngle) {
      this.startAngle = startAngle;
      return this;
    }

    /**
     * Helper conversion setter for y
     * @param row
     * @return
     */
    public Builder row(int row) {
      this.y = row * size + delta;
      return this;
    }

    /**
     * Helper conversion setter for x
     * @param col
     * @return
     */
    public Builder col(int col) {
      this.x = col * size + delta;
      return this;
    }

    private int[] getXCoordinates() {
      int res[] = new int[vertexCount * 2];
      double addAngle = 2 * Math.PI / vertexCount;
      double angle = startAngle;
      double innerAngle = startAngle + Math.PI / vertexCount;
      for (int i = 0; i < vertexCount; i++) {
        res[i * 2] = (int)Math.round(r * Math.cos(angle)) + x;
        angle += addAngle;
        res[i * 2 + 1] = (int)Math.round(innerR * Math.cos(innerAngle)) + x;
        innerAngle += addAngle;
      }
      return res;
    }

    private int[] getYCoordinates() {
      int res[] = new int[vertexCount * 2];
      double addAngle = 2 * Math.PI / vertexCount;
      double angle = startAngle;
      double innerAngle = startAngle + Math.PI / vertexCount;
      for (int i = 0; i < vertexCount; i++) {
        res[i * 2] = (int)Math.round(r * Math.sin(angle)) + y;
        angle += addAngle;
        res[i * 2 + 1] = (int)Math.round(innerR * Math.sin(innerAngle)) + y;
        innerAngle += addAngle;
      }
      return res;
    }

    private StarPolygon build() {
      MorePreconditions.checkNonNegativeIntegers(x, y, innerR);
      MorePreconditions.checkPositiveIntegers(vertexCount);
      xCoordinates = getXCoordinates();
      yCoordinates = getYCoordinates();
      return new StarPolygon(this);
    }
  }
}
