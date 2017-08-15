package com.djd.fun.tachchapter.demo003threads.forkjoinpool;

import java.util.concurrent.RecursiveTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fibonacci extends RecursiveTask<Integer> {

  private static final Logger log = LoggerFactory.getLogger(Fibonacci.class);

  private final int n;

  public Fibonacci(int n) {
    this.n = n;
  }

  @Override
  protected Integer compute() {
    log.info("fibo({})", n);
    if (n <= 1) {
      return n;
    }
    Fibonacci f1 = new Fibonacci(n - 1);
    f1.fork();
    Fibonacci f2 = new Fibonacci(n - 2);
    f2.fork();
    return f2.join() + f1.join();
  }
}