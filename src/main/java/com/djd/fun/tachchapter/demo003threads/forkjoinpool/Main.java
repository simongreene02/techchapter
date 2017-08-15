package com.djd.fun.tachchapter.demo003threads.forkjoinpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] params) throws ExecutionException, InterruptedException {
    int n = 7;
    ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    Fibonacci fib = new Fibonacci(n);
    forkJoinPool.submit(fib);
    log.info("fibonacci({}) = {}", n, fib.get());
  }
}
