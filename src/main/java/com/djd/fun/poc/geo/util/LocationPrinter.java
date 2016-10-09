package com.djd.fun.poc.geo.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import com.djd.fun.poc.geo.datatype.Location;
import com.djd.fun.poc.geo.event.TerminalSignal;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;

/**
 * @author JGD
 * @since 10/8/16
 */
public class LocationPrinter {

  private final AtomicBoolean atomicBoolean;
  private final String fileName;
  private final List<Location> locations;

  public LocationPrinter(String fileName) {
    atomicBoolean = new AtomicBoolean();
    this.fileName = fileName;
    locations = Lists.newArrayListWithCapacity(1000); // tyring to match this to Trip.DEFAULT_DROP_POINTS
  }

  @Subscribe
  public void addLocation(Location location) {
    locations.add(location);
  }

  @Subscribe
  public void dump(TerminalSignal terminalSignal) {
    // print only once.
    if (!atomicBoolean.getAndSet(true)) {
      Iterable<String> txtLocations = locations.stream()
          .map(Object::toString)
          .collect(Collectors.toList());
      try {
        Files.write(Paths.get(fileName), txtLocations, Charset.defaultCharset());
      } catch (IOException e) {
        throw Throwables.propagate(e);
      }
    }
  }
}
