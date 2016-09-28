package com.djd.fun.poc.geo.datatype;

import com.djd.fun.poc.geo.datatype.Location;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author JGD
 * @since 9/25/16
 */
public class LocationTest {

  @Test
  public void checkLatitude_90_ok() {
    Location.checkLatitude(90);
  }

  @Test
  public void checkLatitude_negative90_ok() {
    Location.checkLatitude(-90);
  }

  @Test
  public void checkLatitude_0_ok() {
    Location.checkLatitude(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkLatitude_91_error() {
    Location.checkLatitude(91);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkLatitude_negative91_error() {
    Location.checkLatitude(-91);
  }

  @Test
  public void checkLongitude_180_ok() {
    Location.checkLongitude(180);
  }

  @Test
  public void checkLongitude_negative180_ok() {
    Location.checkLongitude(-180);
  }

  @Test
  public void checkLongitude_0_ok() {
    Location.checkLongitude(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkLongitude_181_error() {
    Location.checkLongitude(181);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkLongitude_negative181_error() {
    Location.checkLongitude(-181);
  }

  public void getInRadians() {
    Location location = Location.with(30, 40);
    assertThat(location.getLatitudeInRadians()).isWithin(0).of(Math.toRadians(30));
    assertThat(location.getLongitudeInRadians()).isWithin(0).of(Math.toRadians(40));
  }

}