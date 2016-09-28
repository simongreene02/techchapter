package com.djd.fun.poc.geo.util;

import com.djd.fun.poc.geo.datatype.Location;
import com.djd.fun.poc.geo.datatype.Trip;

/**
 * static helper class to compute azimuth value from two {@link com.djd.fun.poc.geo.datatype.Location}s
 * <a href="http://www.movable-type.co.uk/scripts/latlong.html">Bearing algorithm</a>
 *
 * @author JGD
 * @since 9/27/16
 */
public class Azimuth {
  private Azimuth() {
    // prevent instance creation
  }
  public static double of(Trip trip) {
    return of(trip.getFrom(), trip.getTo());
  }

  public static double of(Location from, Location to) {
    double lat1 = from.getLatitudeInRadians();
    double lon1 = from.getLongitudeInRadians();
    double lat2 = to.getLatitudeInRadians();
    double lon2 = to.getLongitudeInRadians();

    double deltaLon = lon2 - lon1;
    double y = Math.sin(deltaLon) * Math.cos(lat2);
    double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(deltaLon);
    return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
  }
}
