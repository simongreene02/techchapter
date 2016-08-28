package com.djd.fun.tachchapter.demo004;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.djd.fun.tachchapter.demo003.Threads.stop;

/**
 * @author JGD
 * @since 8/28/16
 */
public class RaceCondition {
  private static final Logger log = LoggerFactory.getLogger(RaceCondition.class);
  // mutable count
  private int count;
  private int countSync;

  public static void main(String[] params) {
    new RaceCondition().watchOut();
  }

  public void watchOut() {
    ExecutorService service = Executors.newWorkStealingPool();
    try {
      IntStream.range(0, 10000).forEach(i -> service.submit(this::increment));
      IntStream.range(0, 10000).forEach(i -> service.submit(this::incrementSync));

    } finally {
      stop(service);
    }
    log.info("count={}", count);
    log.info("countSync={}", countSync);
  }

  // Runnable run method body : task cause race condition
  public void increment() {
    count++;
  }

  /**
   * Runnable run method body : task cause race condition
   * This method prevents race condition
   */
  public synchronized void incrementSync() {
    countSync++;
  }
}
