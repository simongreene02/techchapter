package com.djd.fun.tachchapter.demo009fibonacci;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author JGD
 * @since 10/4/16
 */
public class MatrixFiboTest {

  private static final BigInteger[][] PAYLOAD = new BigInteger[][]{
      {BigInteger.ONE, BigInteger.ONE},
      {BigInteger.ONE, BigInteger.ZERO},
  };

  private Fibonacci fibonacci;

  @Before
  public void setUp() {
    fibonacci = new MatrixFibo();
  }

  @Test
  public void findAt_5_5() {
    assertThat(fibonacci.findAt(5)).isEqualTo(5);
  }

  @Test
  public void findAt_100_354224848179261915075() {
    assertThat(fibonacci.findBigAt(BigInteger.valueOf(100)))
        .isEqualTo(new BigInteger("354224848179261915075"));
  }

  @Test
  public void multiply() {
    final BigInteger[][] expected = new BigInteger[][]{
        {BigInteger.valueOf(23), BigInteger.valueOf(32)},
        {BigInteger.valueOf(18), BigInteger.valueOf(29)},
    };
    final BigInteger[][] a = new BigInteger[][]{
        {BigInteger.ONE, BigInteger.valueOf(5)},
        {BigInteger.valueOf(2), BigInteger.valueOf(3)},
    };
    final BigInteger[][] b = new BigInteger[][]{
        {BigInteger.valueOf(3), BigInteger.valueOf(7)},
        {BigInteger.valueOf(4), BigInteger.valueOf(5)},
    };
    BigInteger[][] result = MatrixFibo.multiply(a, b);
    print(result);
    assertThat(Arrays.deepEquals(result, expected)).isTrue();
  }

  private static void print(BigInteger[][] matrix) {
    Arrays.stream(matrix)
        .forEach(row -> {
          Arrays.stream(row).forEach(System.out::print);
          System.out.println();
        });
  }
}