package com.djd.fun.poc.geo.datatype;

/**
 * Immutable distance data type for given two {@link Location}s
 * <p>
 * <a href="https://en.wikipedia.org/wiki/Haversine_formula">HaversineDistance Formula</a>
 * <a href="https://rosettacode.org/wiki/Haversine_formula#Java">Sample</a>
 *
 * @author JGD
 * @since 9/25/16
 */
public class HaversineDistance {
  private static final double EARTH_RADIUS = 6372.8; // Earth radius in kilometers

  public enum Unit {
    KILOMETERS(1), MILES(0.621371), METERS(1000);

    private final double value;

    Unit(double value) {
      this.value = value;
    }

    public double getValue() {
      return value;
    }
  }

  private final double distanceInKilometers;

  private HaversineDistance(Location from, Location to) {
    distanceInKilometers = initialize(from, to);
  }

  public static HaversineDistance with(Location from, Location to) {
    return new HaversineDistance(from, to);
  }

  public double getDistance(Unit unit) {
    return distanceInKilometers * unit.getValue();
  }

  /**
   * Computes statute distance between two locations in kilometers
   *
   * @param from {@link Location}
   * @param to {@link Location}
   * @return distance in kilometers
   */
  private static double initialize(Location from, Location to) {
    if (from.equals(to)) {
      return 0;
    }
    // convert degree to radians
    final double deltaLat = Math.toRadians(to.getLatitude() - from.getLatitude());
    final double deltaLon = Math.toRadians(to.getLongitude() - from.getLongitude());
    final double fromLat = Math.toRadians(from.getLatitude());
    final double toLat = Math.toRadians(to.getLatitude());

    final double value = sineSquared(deltaLat / 2) +
        Math.cos(fromLat) * Math.cos(toLat) * sineSquared(deltaLon / 2);
    return 2 * EARTH_RADIUS * Math.asin(Math.sqrt(value));
  }

  /**
   * sine squared value
   *
   * @param value
   * @return sin^2(value) or (sin(value))^2
   */
  private static double sineSquared(double value) {
    return Math.pow(Math.sin(value), 2);
  }
}
