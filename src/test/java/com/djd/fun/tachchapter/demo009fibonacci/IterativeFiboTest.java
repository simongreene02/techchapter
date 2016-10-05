package com.djd.fun.tachchapter.demo009fibonacci;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;


/**
 * @author JGD
 * @since 10/4/16
 */
public class IterativeFiboTest {
  private Fibonacci fibonacci;

  @Before
  public void setUp() {
    fibonacci = new IterativeFibo();
  }

  @Test(expected = IllegalArgumentException.class)
  public void findAt_negative() {
    fibonacci.findAt(-1);
  }

  @Test
  public void findAt_0_0() {
    assertThat(fibonacci.findAt(0)).isEqualTo(0);
  }

  @Test
  public void findAt_1_1() {
    assertThat(fibonacci.findAt(1)).isEqualTo(1);
  }

  @Test
  public void findAt_2_1() {
    assertThat(fibonacci.findAt(2)).isEqualTo(1);
  }

  @Test
  public void findAt_3_2() {
    assertThat(fibonacci.findAt(3)).isEqualTo(2);
  }

  @Test
  public void findAt_4_3() {
    assertThat(fibonacci.findAt(4)).isEqualTo(3);
  }

  @Test
  public void findAt_5_5() {
    assertThat(fibonacci.findAt(5)).isEqualTo(5);
  }

  @Test
  public void findAt_6_8() {
    assertThat(fibonacci.findAt(6)).isEqualTo(8);
  }

  @Test
  public void findAt_7_13() {
    assertThat(fibonacci.findAt(7)).isEqualTo(13);
  }

  @Test
  public void findAt_20_6765() {
    assertThat(fibonacci.findAt(20)).isEqualTo(6765);
  }

  @Test
  public void findAt_46_1836311903() {
    assertThat(fibonacci.findAt(46)).isEqualTo(1836311903);
  }

  @Test
  public void findBigAt_46_1836311903() {
    assertThat(fibonacci.findBigAt(BigInteger.valueOf(46))).isEqualTo(BigInteger.valueOf(1836311903));
  }

  @Test
  public void findBigAt_100_354224848179261915075() {
    assertThat(fibonacci.findBigAt(BigInteger.valueOf(100)))
        .isEqualTo(new BigInteger("354224848179261915075"));
  }

  @Test
  public void findBigAt_1000_veryBigNumber() {
    assertThat(fibonacci.findBigAt(BigInteger.valueOf(1000)))
        .isEqualTo(new BigInteger("43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875"));
  }
}