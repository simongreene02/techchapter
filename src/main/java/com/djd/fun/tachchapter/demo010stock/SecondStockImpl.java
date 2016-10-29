package com.djd.fun.tachchapter.demo010stock;

/**
 * Second attempt to solve this in O(n).
 * Bug: down hill prices always results in 0 profit instead of negative value.
 *
 * @author JGD
 * @since 10/23/16
 */
class SecondStockImpl implements Stock {
  @Override
  public int getMaxProfile(int[] stockPrices) {
    if (stockPrices == null || stockPrices.length < 2) {
      throw new IllegalArgumentException("Bad input.");
    }

    int minPrice = stockPrices[0];
    int maxProfit = 0;
    for (int curPrice : stockPrices) {
      if (minPrice > curPrice) {
        minPrice = curPrice;
      }
      maxProfit = Math.max(maxProfit, curPrice - minPrice);
    }
    return maxProfit;
  }
}
