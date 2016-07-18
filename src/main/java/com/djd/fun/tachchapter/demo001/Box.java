package com.djd.fun.tachchapter.demo001;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic Box takes item of type T
 */
public class Box<T extends Number> {
  private final List<T> items;

  public Box() {
    this.items = new ArrayList<T>();
  }

  public T getFirstItem() {
    return items.get(0);
  }

  public void addItem(T item) {
    items.add(item);
  }
}
