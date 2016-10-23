package com.djd.fun.tachchapter.demo010stock;

/**
 * Suppose we could access yesterday's stock prices as an array, where:
 * <p>
 * The indices are the time in minutes past trade opening time, which was 9:30am local time.
 * The values are the price in dollars of Apple stock at that time.
 * So if the stock cost $500 at 10:30am, stockPricesYesterday[60] = 500;.
 * <p>
 * Write an efficient function that takes stockPricesYesterday and returns the best profit I could
 * have made from 1 purchase and 1 sale of 1 Apple stock yesterday.
 * <pre><code>
 * int[] stockPricesYesterday = new int[]{10, 7, 5, 8, 11, 9};
 *
 * getMaxProfit(stockPricesYesterday);
 * </code></pre>
 * // returns 6 (buying for $5 and selling for $11)
 * source: https://www.interviewcake.com/
 *
 * @author JGD
 * @since 10/23/16
 */
public interface Stock {
  int getMaxProfile(int[] stockPrices);
}
