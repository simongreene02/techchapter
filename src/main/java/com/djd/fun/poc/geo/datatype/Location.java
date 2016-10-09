package com.djd.fun.poc.geo.datatype;

import java.util.Objects;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;

/**
 * Immutable data type for a location (latitude, longitude)
 *
 * @author JGD
 * @since 9/25/16
 */
public class Location {

  private final double latitude; // in degrees
  private final double longitude; // in degrees

  private Location(double latitude, double longitude) {
    this.latitude = checkLatitude(latitude);
    this.longitude = checkLongitude(longitude);
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitudeInRadians() {
    return Math.toRadians(latitude);
  }

  public double getLongitudeInRadians() {
    return Math.toRadians(longitude);
  }

  public static Location with(double latitude, double longitude) {
    return new Location(latitude, longitude);
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Location location = (Location)o;
    return Double.compare(location.latitude, latitude) == 0 &&
        Double.compare(location.longitude, longitude) == 0;
  }

  @Override public int hashCode() {
    return Objects.hash(latitude, longitude);
  }

  @Override
  public String toString() {
    return latitude + "," + longitude;
  }

  @VisibleForTesting static double checkLatitude(double latitude) {
    if (latitude < -90 || 90 < latitude) {
      throw new IllegalArgumentException("Bad latitude " + latitude);
    }
    return latitude;
  }

  @VisibleForTesting static double checkLongitude(double longitude) {
    if (longitude < -180 || 180 < longitude) {
      throw new IllegalArgumentException("Bad longitude " + longitude);
    }
    return longitude;
  }

}
