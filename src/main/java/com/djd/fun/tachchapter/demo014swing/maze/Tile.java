//  Copyright (c) 2016 JGD Licensed under the MIT license
package com.djd.fun.tachchapter.demo014swing.maze;

import com.djd.fun.util.MorePreconditions;

/**
 * Tile represents a position addressable by (x,y) coordinate on a {@link Floor} instance.
 *
 * @author JGD
 * @since 8/15/16
 */
public class Tile {
  public enum TileType {
    W('W', "wall", false),
    H(' ', "Hollow", true),
    U('U', "Up", true),
    D('D', "Down", true),
    T('T', "Token", true),
    G('G', "Gen", true),
    E('E', "Enemy", true),
    P('P', "Player", true);

    private final char printableLetter;

    private final String name;
    private final boolean steppable;
    TileType(char printableLetter, String name, boolean steppable) {
      this.printableLetter = printableLetter;
      this.name = name;
      this.steppable = steppable;
    }

    public char getPrintableLetter() {
      return printableLetter;
    }

    public String getName() {
      return name;
    }

    public boolean isSteppable() {
      return steppable;
    }
  }

  private final int x;
  private final int y;

  private final TileType tileType;

  protected Tile(int x, int y, char tileLetter) {
    this.x = MorePreconditions.checkWholeNumber(x);
    this.y = MorePreconditions.checkWholeNumber(y);
    this.tileType = TileType.valueOf(String.valueOf(tileLetter));
  }

  /**
   * Every tile is by default steppable.
   *
   * @return {@code true}
   */
  public boolean isSteppable() {
    return tileType.isSteppable();
  }

  public TileType getTileType() {
    return tileType;
  }

  @Override
  public String toString() {
    return "[" + tileType.getPrintableLetter() + "]";
  }

  public static Tile of(int x, int y, char letter) {
    return new Tile(x, y, letter);
  }
}
