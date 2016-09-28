package com.djd.fun.poc.geo.datatype;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author JGD
 * @since 9/27/16
 */
public class TripTest {

  @Test
  public void equality() {
    Location chi = Location.with(41.87, -87.62);
    Location la = Location.with(33.94, -118.40);
    assertThat(Trip.between(chi, la)).isEqualTo(Trip.between(chi, la));
    assertThat(Trip.between(chi, la)).isNotEqualTo(Trip.between(la, chi));
  }

}