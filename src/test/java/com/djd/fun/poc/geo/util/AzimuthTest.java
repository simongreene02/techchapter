package com.djd.fun.poc.geo.util;

import com.djd.fun.poc.geo.datatype.Location;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;


/**
 * @author JGD
 * @since 9/27/16
 */
public class AzimuthTest {

  @Test
  public void of_chiLa() {
    Location chi = Location.with(41.87, -87.62);
    Location la = Location.with(33.94, -118.40);
    assertThat(Azimuth.of(chi, la)).isWithin(0.001).of(261.96);
  }

}