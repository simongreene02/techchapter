package com.djd.fun.tachchapter.demo003threads.oldfashion;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Producer implements Runnable {

  private final ConcurrentLinkedQueue<String> queue;

  public Producer(ConcurrentLinkedQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    int i = 0;
    while (i + queue.size() < 10) {
      String produce = "data_" + ++i;
      System.out.println("produce " + produce);
      queue.offer(produce);
      try {
        Thread.sleep(400);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    queue.offer("");
    System.out.println("Finish production");
  }
}
