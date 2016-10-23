package com.djd.fun.tachchapter.demo010stock;

/**
 * @author JGD
 * @since 10/23/16
 */
class ThirdStockImpl implements Stock {
  @Override
  public int getMaxProfile(int[] stockPrices) {
    if (stockPrices == null || stockPrices.length < 2) {
      throw new IllegalArgumentException("Bad input.");
    }
    int minPrice = stockPrices[0];
    int maxProfit = stockPrices[1] - stockPrices[0]; // greedy initializer
    for (int i = 1; i < stockPrices.length; i++) {
      int curPrice = stockPrices[i];
      maxProfit = Math.max(maxProfit, curPrice - minPrice);
      minPrice = Math.min(minPrice, curPrice);
    }
    return maxProfit;
  }
}
