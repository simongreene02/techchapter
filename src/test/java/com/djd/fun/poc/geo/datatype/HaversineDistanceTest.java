package com.djd.fun.poc.geo.datatype;

import org.junit.Test;

import static com.djd.fun.poc.geo.datatype.HaversineDistance.Unit.KILOMETERS;
import static com.google.common.truth.Truth.assertThat;

/**
 * @author JGD
 * @since 9/25/16
 */
public class HaversineDistanceTest {

  @Test
  public void getDistance_zero() {
    HaversineDistance distance = HaversineDistance.with(Location.with(40, 50), Location.with(40, 50));
    assertThat(distance.getDistanceIn(KILOMETERS)).isWithin(0).of(0);
  }

  @Test
  public void getDistance_chicagoToLA() {
    HaversineDistance distance = HaversineDistance.with(
        Location.with(41.87, -87.62), Location.with(33.94, -118.40));
    assertThat(distance.getDistanceIn(KILOMETERS)).isWithin(0.001).of(2823.966);
  }

  @Test
  public void getDistance_nashvilleToLA() {
    HaversineDistance distance = HaversineDistance.with(
        Location.with(36.12, -86.67), Location.with(33.94, -118.40));
    assertThat(distance.getDistanceIn(KILOMETERS)).isWithin(0.001).of(2887.259);
  }

  @Test
  public void equality() {
    Location chi = Location.with(41.87, -87.62);
    Location la = Location.with(33.94, -118.40);
    HaversineDistance distanceChi2LA = HaversineDistance.with(chi, la);
    HaversineDistance distanceLA2Chi = HaversineDistance.with(la, chi);
    assertThat(distanceChi2LA).isEqualTo(distanceLA2Chi);
  }
}