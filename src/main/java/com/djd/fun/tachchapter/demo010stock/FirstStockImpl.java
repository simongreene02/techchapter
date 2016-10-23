package com.djd.fun.tachchapter.demo010stock;

/**
 * First attempt try out every possible combination.
 * @author JGD
 * @since 10/23/16
 */
public class FirstStockImpl implements Stock {
  @Override
  public int getMaxProfile(int[] stockPrices) {
    if (stockPrices == null || stockPrices.length < 2) {
      throw new IllegalArgumentException("Bad input.");
    }

    int maxProfit = Integer.MIN_VALUE;
    for (int i = 0; i < stockPrices.length; i++) {
      for (int j = i + 1; j < stockPrices.length; j++) {
        maxProfit = Math.max(maxProfit, stockPrices[j] - stockPrices[i]);
      }
    }
    return maxProfit;
  }
}
