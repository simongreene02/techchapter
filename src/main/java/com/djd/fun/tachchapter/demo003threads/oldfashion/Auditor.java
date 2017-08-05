package com.djd.fun.tachchapter.demo003threads.oldfashion;

import java.util.Collection;

public class Auditor implements Runnable {

  private final Collection<String> target;

  public Auditor(Collection<String> target) {this.target = target;}

  @Override
  public void run() {
    int zeros = 0;
    while (zeros < 5) {
      if (target.isEmpty()) {
        zeros++;
      } else {
        zeros = 0;
      }
      System.out.println("Current pool size: " + target.size());
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    System.out.println("End of Auditor");
  }
}
