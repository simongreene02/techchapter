//  Copyright (c) 2016 JGD Licensed under the MIT license
package com.djd.fun.tachchapter.demo014swing.maze.models;

import java.util.Objects;

import com.djd.fun.util.MorePreconditions;
import com.google.common.base.MoreObjects;

/**
 * Represents a location on Floor grid (2D array)
 *
 * @author JGD
 * @since 9/10/16
 */
public class Location {
  // lazy anti-encapsulation
  public final int row; // 0 based row index
  public final int col; // 0 based column index

  private Location(int row, int col) {
    MorePreconditions.checkNonNegativeIntegers(row, col);
    this.row = row;
    this.col = col;
  }

  /**
   * static factory helper method to create an instance of {@link Location}
   *
   * @param row
   * @param col
   * @return {@link Location}
   */
  public static Location of(int row, int col) {
    return new Location(row, col);
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Location location = (Location)o;
    return row == location.row && col == location.col;
  }

  @Override public int hashCode() {
    return Objects.hash(row, col);
  }

  @Override public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("row", row)
        .add("col", col)
        .toString();
  }
}
