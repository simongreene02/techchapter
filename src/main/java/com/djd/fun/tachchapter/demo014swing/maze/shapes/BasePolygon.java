package com.djd.fun.tachchapter.demo014swing.maze.shapes;

import java.awt.Polygon;
import java.util.Arrays;
import java.util.Objects;

public class BasePolygon extends Polygon {

  protected BasePolygon(int[] xpoints, int[] ypoints, int npoints) {
    super(xpoints, ypoints, npoints);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BasePolygon that = (BasePolygon)o;
    return npoints == that.npoints &&
        Arrays.equals(xpoints, that.xpoints) &&
        Arrays.equals(ypoints, that.ypoints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(npoints, Arrays.hashCode(xpoints), Arrays.hashCode(ypoints));
  }
}
