package com.djd.fun.tachchapter.demo001;

/**
 * This demo class shows type erasure effects
 */
public class Demo001 {
  public static void main(String [] params) {
    Box<Number> box = new Box();
    box.addItem(5);
    Box<Integer> boxi = new Box();
    boxi.addItem(5);
    Number num = box.getFirstItem();
    Integer numi = boxi.getFirstItem();
  }
}
