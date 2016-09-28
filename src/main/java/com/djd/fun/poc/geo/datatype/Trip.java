package com.djd.fun.poc.geo.datatype;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Immutable data type represents a trip between two {@link Location}s
 * @author JGD
 * @since 9/27/16
 */
public class Trip {
  private final Location from;
  private final Location to;

  private Trip(Location from, Location to) {
    this.from = from;
    this.to = to;
  }

  public Location getFrom() {
    return from;
  }

  public Location getTo() {
    return to;
  }

  public HaversineDistance findDistance() {
    return HaversineDistance.with(from, to);
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
