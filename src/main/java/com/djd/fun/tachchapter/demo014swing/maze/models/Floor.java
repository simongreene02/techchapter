//  Copyright (c) 2016 JGD Licensed under the MIT license
package com.djd.fun.tachchapter.demo014swing.maze.models;

import java.io.PrintStream;

import com.djd.fun.util.MorePreconditions;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class represents a dungeon floor at specified dimension.
 * dimension must be at least 1x1
 *
 * @author JGD
 * @since 8/15/16
 */
public class Floor {

  private static final Logger log = LoggerFactory.getLogger(Floor.class);
  private final int numOfRows;
  private final int numOfCols;

  /**
   * Note: Do create public accessor to this reference. Keep it immutable.
   */
  private final Tile[][] tiles;

  /**
   * This is player's original location. Floor does NOT keep current player location.
   */
  private final Location playerOriginalLocation;
  private final ImmutableSet<Location> enemyLocations;
  private final ImmutableSet<Location> tokenLocations;
  private final ImmutableSet<Location> gemLocations;

  /**
   * Create an instance of floor based on given floorPlan
   *
   * @param floorPlan has to be at least 1x1 size.
   */
  public Floor(String[][] floorPlan) {
    checkNotNull(floorPlan);

    // TODO do floorPlan size validation
    if (floorPlan.length < 1 || floorPlan[0].length < 1) {
      throw new IllegalArgumentException("floor must be at least 1x1");
    }

    this.numOfRows = floorPlan.length;
    this.numOfCols = floorPlan[0].length;

    ImmutableSet.Builder<Location> tokenLocationBuilder = ImmutableSet.builder();
    ImmutableSet.Builder<Location> enemyLocationBuilder = ImmutableSet.builder();
    ImmutableSet.Builder<Location> gemLocationBuilder = ImmutableSet.builder();
    tiles = new Tile[floorPlan.length][floorPlan[0].length];
    Location playerLocation = null;
    for (int row = 0; row < numOfRows; row++) {
      for (int col = 0; col < numOfCols; col++) {
        String letter = floorPlan[row][col];
        if (Strings.isNullOrEmpty(letter)) {
          throw new IllegalArgumentException(
              String.format("Bad floorPlan on [%d][%d] is null", row, col));
        }
        char tileType = letter.charAt(0);
        // identify player location
        if ('P' == tileType) {
          playerLocation = Location.of(row, col);
        } else if ('T' == tileType) {
          tokenLocationBuilder.add(Location.of(row, col));
        } else if ('E' == tileType) {
          enemyLocationBuilder.add(Location.of(row, col));
        } else if ('G' == tileType) {
          gemLocationBuilder.add(Location.of(row, col));
        }
        tiles[row][col] = Tile.of(row, col, tileType);
      }
    }
    this.playerOriginalLocation = checkNotNull(playerLocation);
    this.enemyLocations = enemyLocationBuilder.build();
    this.tokenLocations = tokenLocationBuilder.build();
    this.gemLocations = gemLocationBuilder.build();
  }

  public int getNumOfRows() {
    return numOfRows;
  }

  public int getNumOfCols() {
    return numOfCols;
  }

  public Tile.TileType getTileType(Location location) {
    return getTileType(location.row, location.col);
  }

  public Tile.TileType getTileType(int row, int col) {
    MorePreconditions.checkWholeNumber(row);
    MorePreconditions.checkWholeNumber(col);
    if (row >= numOfRows || col >= numOfCols) {
      throw new IllegalArgumentException("[row,col] is out of bound.");
    }
    return tiles[row][col].getTileType();
  }

  public ImmutableSet<Location> getTokenLocations() {
    return tokenLocations;
  }

  public ImmutableSet<Location> getEnemyLocations() {
    return enemyLocations;
  }

  public ImmutableSet<Location> getGemLocations() {
    return gemLocations;
  }

  public boolean isEnemyAt(Location location) {
    isValidLocation(location);
    return Tile.TileType.E == tiles[location.row][location.col].getTileType();
  }

  /**
   * Return north location of specified location on the grid.
   *
   * @param location current player location
   * @return north {@link Location} if the player character is allowed to enter the target location.
   * current location otherwise.
   */
  public Location getNorthLocation(Location location) {
    if (location.row > 0 && location.row < numOfRows) {
      Location destination = Location.of(location.row - 1, location.col);
      if (canEnter(destination)) {
        return destination;
      }
    }
    return location;
  }

  /**
   * Return east location of specified location on the grid.
   *
   * @param location current player location
   * @return east {@link Location} if the player character is allowed to enter the target location.
   * current location otherwise.
   */
  public Location getEastLocation(Location location) {
    if (location.col < numOfCols - 1) {
      Location destination = Location.of(location.row, location.col + 1);
      if (canEnter(destination)) {
        return destination;
      }
    }
    return location;
  }

  /**
   * Return south location of specified location on the grid.
   *
   * @param location current player location
   * @return south {@link Location} if the player character is allowed to enter the target location.
   * current location otherwise.
   */
  public Location getSouthLocation(Location location) {
    if (location.row < numOfRows - 1) {
      Location destination = Location.of(location.row + 1, location.col);
      if (canEnter(destination)) {
        return destination;
      }
    }
    return location;
  }

  /**
   * Return west location of specified location on the grid.
   *
   * @param location current player location
   * @return west {@link Location} if the player character is allowed to enter the target location.
   * current location otherwise.
   */
  public Location getWestLocation(Location location) {
    if (location.col > 0 && location.col < numOfCols) {
      Location destination = Location.of(location.row, location.col - 1);
      if (canEnter(destination)) {
        return destination;
      }
    }
    return location;
  }

  /**
   * Check if specified tile can be stepable
   *
   * @param location may or may not be valid on the grid
   * @return {@code true} if tile at the specified location is not Wall nor out of boundary.
   */
  public boolean canEnter(Location location) {
    if (isValidLocation(location)) {
      return tiles[location.row][location.col].isSteppable();
    }
    log.warn("Invalid location {}", location);
    return false;
  }

  public void printBoard(PrintStream out) {
    for (int row = 0; row < tiles.length; row++) {
      for (int col = 0; col < tiles[0].length; col++) {
        out.print(tiles[row][col]);
      }
      out.println();
    }
  }

  /**
   * This should be used only in initialization phase
   *
   * @return original player location.
   */
  public Location getOriginalPlayerLocation() {
    return playerOriginalLocation;
  }

  @VisibleForTesting boolean isValidLocation(Location location) {
    return location.row >= 0 && location.row < numOfRows &&
        location.col >= 0 && location.col < numOfCols;
  }
}
