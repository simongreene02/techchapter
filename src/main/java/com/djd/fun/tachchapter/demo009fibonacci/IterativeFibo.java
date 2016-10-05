package com.djd.fun.tachchapter.demo009fibonacci;

import java.math.BigInteger;

/**
 * @author JGD
 * @since 10/4/16
 */
public class IterativeFibo implements Fibonacci {

  /**
   * static method to find out N-th fibonacci number.
   * <p>
   * Note: Time complexity of iterative implementation is O(N).
   *
   * @param n valid range 0 and 16
   * @return Nth fibonacci number
   */
  @Override
  public int findAt(int n) {
    if (n < 0 || n > 46) {
      throw new IllegalArgumentException("Acceptable range: 0 <= N <= 46");
    }

    // case where n=0 or n=1
    if (n < 2) {
      return n;
    }

    // n >= 2
    int previous = 0;
    int previousPrevious = 1;
    for (int i = 2; i < n; i++) {
      int tmp = previous;
      previous = previousPrevious;
      previousPrevious += tmp;
    }
    return previous + previousPrevious;
  }

  @Override public BigInteger findBigAt(BigInteger n) {
    if (n.compareTo(BigInteger.ZERO) < 0 || BigInteger.valueOf(1000).compareTo(n) < 0) {
      throw new IllegalArgumentException("Acceptable range: 0 <= N <= 1000");
    }

    // case where n=0 or n=1
    if (n.compareTo(BigInteger.ZERO) == 0 || n.compareTo(BigInteger.ONE) == 0) {
      return n;
    }

    // n >= 2
    BigInteger previous = BigInteger.ZERO;
    BigInteger previousPrevious = BigInteger.ONE;
    BigInteger currentSequenceIndex = BigInteger.valueOf(2);
    while (currentSequenceIndex.compareTo(n) < 0) {
      BigInteger tmp = previous.add(previousPrevious);
      previous = previousPrevious;
      previousPrevious = tmp;
      currentSequenceIndex = currentSequenceIndex.add(BigInteger.ONE);
    }
    return previous.add(previousPrevious);
  }
}
