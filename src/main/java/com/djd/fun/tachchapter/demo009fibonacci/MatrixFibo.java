package com.djd.fun.tachchapter.demo009fibonacci;

import java.math.BigInteger;

import com.google.common.annotations.VisibleForTesting;

/**
 * Matrix Fibonacci runs O(log N) and faster than iterative implementation if N is relatively large.
 * According to
 * <a href="http://kukuruku.co/hub/algorithms/the-nth-fibonacci-number-in-olog-n">Source</a>
 * when N > 250 MatrixFibo runs faster than IterativeFibo
 *
 * @author JGD
 * @since 10/4/16
 */
public class MatrixFibo implements Fibonacci {

  /**
   * static method to find out N-th fibonacci number.
   * <p>
   * Note: Time complexity of Matrix implementation is O(log N).
   *
   * @param n > 0
   * @return Nth fibonacci number
   */
  @Override
  public int findAt(int n) {
    return findBigAt(BigInteger.valueOf(n)).intValue();
  }

  @Override public BigInteger findBigAt(BigInteger n) {
    if (n.compareTo(BigInteger.ZERO) < 0) {
      throw new IllegalArgumentException("N needs to be non-negative integer.");
    }

    // case where n=0 or n=1
    if (n.compareTo(BigInteger.ZERO) == 0 || n.compareTo(BigInteger.ONE) == 0) {
      return n;
    }

    // n >= 2

    final BigInteger[][] baseMatrix = new BigInteger[][]{
        {BigInteger.ONE, BigInteger.ONE},
        {BigInteger.ONE, BigInteger.ZERO},
    };
    BigInteger[][] result = baseMatrix;
    // TODO may be do this in BigInteger all the way??
    final int upto = n.intValue();
    for (int i = 2; i < upto; i++) {
      result = multiply(result, baseMatrix);
    }
    return result[0][0];
  }

  /**
   *
   * @param a 2x2 matrix
   * @param b 2x2 matrix
   * @return a * b
   */
  @VisibleForTesting static BigInteger[][] multiply(BigInteger[][] a, BigInteger[][] b) {
    BigInteger[][] result = new BigInteger[2][2];
    result[0][0] = a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0]));
    result[0][1] = a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]));
    result[1][0] = a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0]));
    result[1][1] = a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]));
    return result;
  }
}
