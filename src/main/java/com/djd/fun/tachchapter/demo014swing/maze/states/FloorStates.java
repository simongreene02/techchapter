package com.djd.fun.tachchapter.demo014swing.maze.states;

import java.util.Collection;

import com.djd.fun.tachchapter.demo014swing.maze.models.FloorFactory;
import com.djd.fun.tachchapter.demo014swing.maze.models.Location;
import com.djd.fun.tachchapter.demo014swing.maze.models.Tile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FloorStates {

  private static final Logger log = LoggerFactory.getLogger(FloorStates.class);
  private int currentFloorIndex;
  private final FloorState[] floorStates;

  public FloorStates() {
    this.currentFloorIndex = 0;
    FloorFactory floorFactory = new FloorFactory();
    this.floorStates = new FloorState[]{
        new FloorState(floorFactory.loadFloor("001")),
        new FloorState(floorFactory.loadFloor("002"))
    };
  }

  public Location getOriginalPlayerLocation() {
    return floorStates[0].getFloor().getOriginalPlayerLocation();
  }

  public boolean isEnemyAt(Location location) {
    return floorStates[currentFloorIndex].isEnemyAt(location);
  }

  public boolean removeEnemyAt(Location location) {
    return floorStates[currentFloorIndex].removeEnemyAt(location);
  }

  public boolean removeTokenAt(Location location) {
    return floorStates[currentFloorIndex].removeTokenAt(location);
  }

  public boolean removeGemAt(Location location) {
    return floorStates[currentFloorIndex].removeGemAt(location);
  }

  public boolean noMoreTokens() {
    for (FloorState floorState : floorStates) {
      if (!floorState.noMoreTokens()) {
        return false;
      }
    }
    return true;
  }

  public int getNumOfRows() {
    return floorStates[currentFloorIndex].getFloor().getNumOfRows();
  }

  public int getNumOfCols() {
    return floorStates[currentFloorIndex].getFloor().getNumOfCols();
  }

  public Tile.TileType getTileType(int row, int col) {
    return floorStates[currentFloorIndex].getFloor().getTileType(row, col);
  }

  public Tile.TileType getTileType(Location location) {
    return getTileType(location.row, location.col);
  }

  public boolean hasGemAt(Location location) {
    return floorStates[currentFloorIndex].hasGemAt(location);
  }

  public boolean hasTokenAt(Location location) {
    return floorStates[currentFloorIndex].hasTokenAt(location);
  }

  public Collection<Location> getEnemyLocations() {
    return floorStates[currentFloorIndex].getEnemyLocations();
  }

  public Location getNorthLocation(Location location) {
    return floorStates[currentFloorIndex].getFloor().getNorthLocation(location);
  }

  public Location getSouthLocation(Location location) {
    return floorStates[currentFloorIndex].getFloor().getSouthLocation(location);
  }

  public Location getWestLocation(Location location) {
    return floorStates[currentFloorIndex].getFloor().getWestLocation(location);
  }

  public Location getEastLocation(Location location) {
    return floorStates[currentFloorIndex].getFloor().getEastLocation(location);
  }

  public void refreshEnemyLocations(Collection<Location> locations) {
    floorStates[currentFloorIndex].refreshEnemyLocations(locations);
  }

  public void goUp() {
    if (++currentFloorIndex >= floorStates.length) {
      currentFloorIndex--;
      log.warn("There is no more floor to go Up");
    }
  }

  public void goDown() {
    if (--currentFloorIndex < 0) {
      currentFloorIndex++;
      log.warn("There is no more floor to go Down");
    }
  }
}
