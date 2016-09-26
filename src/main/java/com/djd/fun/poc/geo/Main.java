package com.djd.fun.poc.geo;

import com.djd.fun.poc.geo.datatype.Location;
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

  }

}
