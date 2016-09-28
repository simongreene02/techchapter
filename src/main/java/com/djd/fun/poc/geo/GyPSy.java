package com.djd.fun.poc.geo;

import com.djd.fun.poc.geo.datatype.HaversineDistance;
import com.djd.fun.poc.geo.datatype.Location;
import com.djd.fun.poc.geo.datatype.Trip;
import com.djd.fun.poc.geo.util.Azimuth;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GPS simulator publishes current location to all subscribers via {@link EventBus}
 *
 * @author JGD
 * @since 9/26/16
 */
public class GyPSy implements Runnable {
  private static final Logger log = LoggerFactory.getLogger(GyPSy.class);
  private final EventBus eventBus;
  private final Trip trip;
  private double lat = 1;
  private double lon = 2;

  public GyPSy(EventBus eventBus, Trip trip) {
    this.eventBus = eventBus;
    this.trip = trip;
  }

  @Override
  public void run() {
    double azimuth = Azimuth.of(trip);
    // very delta time unit post new location to eventBus
    // http://www.movable-type.co.uk/scripts/latlong.html
    // line.py with bug free bearing function
    eventBus.post(Location.with(lat++, lon++));
  }

  private static ImmutableList<Location> calculatesRoute() {

    throw new UnsupportedOperationException("TODO");
  }

  /**
   * calculates destination location based on from location, azimuth and distance.
   *
   * @param from {@link Location}
   * @param azimuth direction in degrees
   * @param distanceInMeters distance between {@link Location} from and destination {@link Location}
   * @return destination {@link Location}
   */
  @VisibleForTesting static Location findDestinationLocation(Location from, double azimuth,
      double distanceInMeters) {
    double bearing = Math.toRadians(azimuth);
    double fromLat = from.getLatitudeInRadians();
    double fromLon = from.getLongitudeInRadians();
    double distanceInKilometers = distanceInMeters / 1000 / HaversineDistance.EARTH_RADIUS;
    log.info("bearing: {}, distanceInKilo: {}", bearing, distanceInKilometers);
    double toLat = Math.asin(Math.sin(fromLat) * Math.cos(distanceInKilometers) +
        Math.cos(fromLat) * Math.sin(distanceInKilometers) * Math.cos(bearing));
    double y = Math.sin(bearing) * Math.sin(distanceInKilometers) * Math.cos(fromLat);
    double x = Math.cos(distanceInKilometers) - Math.sin(fromLat) * Math.sin(toLat);
    double toLon = fromLon + Math.atan2(y, x);
    return Location.with(Math.toDegrees(toLat), Math.toDegrees(toLon));
  }
}
