package com.djd.fun.poc.geo;

import com.djd.fun.poc.geo.datatype.Location;
import com.google.common.eventbus.EventBus;

/**
 * GPS simulator publishes current location to all subscribers via {@link EventBus}
 * @author JGD
 * @since 9/26/16
 */
public class GyPSy implements Runnable {
  private final EventBus eventBus;

  public GyPSy(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  @Override
  public void run() {
    // very delta time unit post new location to eventBus
    // http://www.movable-type.co.uk/scripts/latlong.html
    // line.py with bug free bearing function
    eventBus.post(Location.with(8,7));
  }
}
