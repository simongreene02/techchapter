package com.djd.fun.tachchapter.demo014swing.maze.states;

import java.util.Collection;
import java.util.Set;

import com.djd.fun.tachchapter.demo014swing.maze.Floor;
import com.djd.fun.tachchapter.demo014swing.maze.Location;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class FloorState {

  private final Floor floor;
  private final Set<Location> remainingGemLocations;
  private final Set<Location> remainingTokenLocations;
  private final Set<Location> enemyLocations;

  public FloorState(Floor floor) {
    this.floor = floor;
    this.remainingGemLocations = Sets.newHashSet(floor.getGemLocations());
    this.remainingTokenLocations = Sets.newHashSet(floor.getTokenLocations());
    this.enemyLocations = Sets.newConcurrentHashSet(floor.getEnemyLocations());
  }

  public Floor getFloor() {
    return floor;
  }

  public boolean isEnemyAt(Location location) {
    return enemyLocations.contains(location);
  }

  public boolean removeEnemyAt(Location location) {
    return enemyLocations.remove(location);
  }

  public boolean removeGemAt(Location location) {
    return remainingGemLocations.remove(location);
  }

  public boolean removeTokenAt(Location location) {
    return remainingTokenLocations.remove(location);
  }

  public boolean noMoreTokens() {
    return remainingTokenLocations.isEmpty();
  }

  public boolean hasGemAt(Location location) {
    return remainingGemLocations.contains(location);
  }

  public boolean hasTokenAt(Location location) {
    return remainingTokenLocations.contains(location);
  }

  public Collection<Location> getEnemyLocations() {
    return ImmutableSet.copyOf(enemyLocations);
  }

  public void refreshEnemyLocations(Collection<Location> locations) {
    enemyLocations.clear();
    enemyLocations.addAll(locations);
  }
}
