package com.djd.fun.tachchapter.demo010stock;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author JGD
 * @since 10/23/16
 */
public class SecondStockImplTest {
  private Stock stock;
  
  @Before
  public void setUp() {
    stock = new SecondStockImpl();
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getMaxProfile_nullArray_error() {
    stock.getMaxProfile(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getMaxProfile_emptyArray_error() {
    stock.getMaxProfile(new int[]{});
  }

  @Test(expected = IllegalArgumentException.class)
  public void getMaxProfile_oneItemArray_error() {
    stock.getMaxProfile(new int[]{1});
  }

  @Test
  public void getMaxProfile_validInput_6() {
    assertThat(stock.getMaxProfile(new int[]{10, 7, 5, 8, 11, 9})).isEqualTo(6);
  }

  @Test
  public void getMaxProfile_noChange_0() {
    assertThat(stock.getMaxProfile(new int[]{10, 10, 10})).isEqualTo(0);
  }

  @Test
  public void getMaxProfile_trickyInput_6() {
    assertThat(stock.getMaxProfile(new int[]{10, 7, 5, 8, 11, 4, 3})).isEqualTo(6);
  }

  @Test
  public void getMaxProfile_downHillChange_0() {
    // TODO I want this return value to be -2
    assertThat(stock.getMaxProfile(new int[]{5, 3, 1})).isEqualTo(0);
  }

}