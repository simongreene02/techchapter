package com.djd.fun.poc.geo;

import com.djd.fun.poc.geo.datatype.HaversineDistance;
import com.djd.fun.poc.geo.datatype.Location;
import com.google.common.eventbus.Subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stateful class responsible for interacting virtual GPS simulator (aka GyPSy)
 *
 * @author JGD
 * @since 9/25/16
 */
public class LeAtitude {
  private static final Logger log = LoggerFactory.getLogger(LeAtitude.class);
  private final Location originLocation;
  private final Location destinationLocation;
  private final HaversineDistance totalDistance;
  private Location currentLocation;

  public LeAtitude(Location originLocation, Location destinationLocation) {
    this.originLocation = originLocation;
    this.destinationLocation = destinationLocation;
    this.totalDistance = HaversineDistance.with(originLocation, destinationLocation);
    currentLocation = originLocation;
  }

  /**
   * subscriber callback method
   */
  @Subscribe
  public void recordCurrentLocationChange(Location currentLocation) {
    this.currentLocation = currentLocation;
    log.info("current location updated: {}", currentLocation);
  }

  public Location getCurrentLocation() {
    return currentLocation;
  }

  /**
   * distance between originLocation to destinationLocation
   *
   * @return {@link HaversineDistance}
   */
  public HaversineDistance getTotalDistance() {
    return totalDistance;
  }

  /**
   * distance between currentLocation to destinationLocation
   *
   * @return {@link HaversineDistance}
   */
  public HaversineDistance getRemainingDistance() {
    return HaversineDistance.with(currentLocation, destinationLocation);
  }
}
