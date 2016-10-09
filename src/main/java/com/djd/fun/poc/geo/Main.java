package com.djd.fun.poc.geo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.djd.fun.poc.geo.datatype.Location;
import com.djd.fun.poc.geo.datatype.Trip;
import com.djd.fun.poc.geo.util.LocationPrinter;
import com.google.common.eventbus.EventBus;

/**
 * GyPSy: GPS simulator runs on a background thread every scheduled time interval GyPSy publish the
 * simulated {@link Location} to {@link EventBus}
 * <p>
 * LeAtitude: one of {@link EventBus} subscribers reacts to the published GPS location and take
 * an action accordingly.
 * <p>
 * <a href="http://www.darrinward.com/lat-long/?id=2281040">map view</a>
 *
 * @author JGD
 * @since 9/25/16
 */

public class Main {
  private static final Location ORD = Location.with(41.9794073, -87.9081644);
  private static final Location LAX = Location.with(33.94, -118.40);
  private static final Location NRT = Location.with(35.7719867,140.3906614);
  private static final Location AKL = Location.with(-36.8626942, 174.58528);

  public static void main(String[] params) {
    Trip trip = Trip.between(ORD, AKL);

    LeAtitude leAtitude = new LeAtitude(trip);
    EventBus eventBus = new EventBus();
    eventBus.register(leAtitude);

    eventBus.register(new LocationPrinter("/tmp/latlon.txt"));
    GyPSy gypsy = new GyPSy(eventBus, trip);
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    scheduledExecutorService.scheduleWithFixedDelay(gypsy, 0, 2, TimeUnit.MILLISECONDS);
  }
}
