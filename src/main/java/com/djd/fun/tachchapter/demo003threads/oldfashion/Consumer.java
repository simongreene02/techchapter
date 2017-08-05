package com.djd.fun.tachchapter.demo003threads.oldfashion;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Consumer implements Runnable {

  private final ConcurrentLinkedQueue<String> queue;

  public Consumer(ConcurrentLinkedQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run(){
    boolean exit = false;
    while (!exit) {
      while (!queue.isEmpty()) {
        String data = queue.poll();
        if (data.equals("")) {
          exit = true;
        }
        System.out.println("Consume " + data);
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    System.out.println("End of Consumer");
  }
}