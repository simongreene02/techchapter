package com.djd.fun.tachchapter.demo003threads.oldfashion;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.common.collect.ImmutableList;

public class SimpleThread {
  public static void main(String[] params) {
    ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();
    new Market(ImmutableList.of(new Auditor(queue), new Producer(queue), new Consumer(queue))).start();
  }
}
