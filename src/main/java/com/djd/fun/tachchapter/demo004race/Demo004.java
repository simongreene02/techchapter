package com.djd.fun.tachchapter.demo004race;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.djd.fun.tachchapter.demo003threads.Threads.stop;

/**
 * Race-condition with non-atomic int.
 * <p>
 * Run this program, you will see non-deterministic count.
 * <pre>
 * [main] INFO com.djd.fun.tachchapter.demo004race.Demo004 - count=3706
 * </pre>
 *
 * @author JGD
 * @since 8/28/16
 */
public class Demo004 {
  private static final Logger log = LoggerFactory.getLogger(Demo004.class);

  public static void main(String[] param) {
    NonAtomicInt nonAtomicInt = new NonAtomicInt();
    ExecutorService service = Executors.newWorkStealingPool();
    try {
      IntStream.range(0, 10000).forEach(i -> service.submit(nonAtomicInt::increment));
    } finally {
      stop(service);
    }
    log.info("count={}", nonAtomicInt.getCount());
  }

  private static class NonAtomicInt {
    // mutable variable
    private int counter;

    /**
     * Cause race condition
     */
    public void increment() {
      counter++;
    }

    /**
     * prevent race condition
     */
    public void incrementSync() {
      synchronized (Demo004.class) {
        counter++;
      }
    }

    public int getCount() {
      return counter;
    }
  }
}
