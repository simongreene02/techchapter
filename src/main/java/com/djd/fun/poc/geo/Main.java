package com.djd.fun.poc.geo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.djd.fun.poc.geo.datatype.Location;
import com.djd.fun.poc.geo.datatype.Trip;
import com.google.common.eventbus.EventBus;

/**
 * Create an instance of Observer and Observable.
 * Use concurrency API to run Observable service.
 * Register observer instance to observable service.
 *
 * @author JGD
 * @since 9/25/16
 */

public class Main {

  public static void main(String[] params) {
    Location chicago = Location.with(41.87, -87.62);
    Location la = Location.with(33.94, -118.40);
    LeAtitude leAtitude = new LeAtitude(chicago, la);
    EventBus eventBus = new EventBus();
    eventBus.register(leAtitude);
    GyPSy gyPSy = new GyPSy(eventBus, Trip.between(chicago, la));
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    scheduledExecutorService.scheduleWithFixedDelay(gyPSy, 0, 1, TimeUnit.SECONDS);
  }

}
