package com.djd.fun.poc.geo;

import java.util.List;

import com.djd.fun.poc.geo.datatype.Location;
import com.djd.fun.poc.geo.datatype.Trip;
import com.djd.fun.poc.geo.util.Azimuth;
import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * GPS simulator publishes current location to all subscribers via {@link EventBus}
 *
 * @author JGD
 * @since 9/26/16
 */
public class GyPSy implements Runnable {
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
}
