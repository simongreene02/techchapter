package com.djd.fun.poc.geo;

import com.djd.fun.poc.geo.datatype.Location;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;


/**
 * @author JGD
 * @since 9/27/16
 */
public class GyPSyTest {
  private static final Location CHICAGO = Location.with(41.87, -87.62);
  private static final double LA_DIRECTION = 261.98;

  @Test
  public void findDestinationLocation() {
    Location destination = GyPSy.findDestinationLocation(CHICAGO, LA_DIRECTION, 1200000);
    assertThat(destination.getLatitude()).isWithin(0.001).of(41.854871);
    assertThat(destination.getLongitude()).isWithin(0.001).of(-87.763312);
  }

  @Test
  public void findDestinationLocation_235th() {
    Location destination = GyPSy.findDestinationLocation(CHICAGO, LA_DIRECTION, 235 * 12000);
    assertThat(destination.getLatitude()).isWithin(0.001).of(33.973352);
    assertThat(destination.getLongitude()).isWithin(0.001).of(-118.343783);
  }


}