package com.djd.fun.poc.geo.datatype;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Immutable data type represents a trip between two {@link Location}s
 *
 * @author JGD
 * @since 9/27/16
 */
public class Trip {
  /**
   * number of incremental points between from & to locations
   */
  private static final int DEFAULT_DROP_POINTS = 18;
  private final Location from;
  private final Location to;
  private final HaversineDistance distance;
  private final int numOfDropPoints;
  /**
   * interval in meters between from location and to location
   */
  private final double intervalMeter;

  private Trip(Location from, Location to) {
    this(from, to, DEFAULT_DROP_POINTS);
  }

  private Trip(Location from, Location to, int numOfDropPoints) {
    this.numOfDropPoints = numOfDropPoints;
    this.from = from;
    this.to = to;
    distance = HaversineDistance.with(from, to);
    intervalMeter = distance.getDistanceIn(HaversineDistance.Unit.METERS) / numOfDropPoints;
  }

  public Location getFrom() {
    return from;
  }

  public Location getTo() {
    return to;
  }

  /**
   * @return interval in meter between drop points
   */
  public double getIntervalMeter() {
    return intervalMeter;
  }

  public int getNumOfDropPoints() {
    return numOfDropPoints;
  }

  public HaversineDistance findDistance() {
    return distance;
  }

  public static Trip between(Location from, Location to) {
    return new Trip(from, to);
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Trip trip = (Trip)o;
    return Objects.equals(from, trip.from) &&
        Objects.equals(to, trip.to);
  }

  @Override public int hashCode() {
    return Objects.hash(from, to);
  }

  @Override public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("from", from)
        .add("to", to)
        .toString();
  }
}
