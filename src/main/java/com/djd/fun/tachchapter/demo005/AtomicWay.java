package com.djd.fun.tachchapter.demo005;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.djd.fun.tachchapter.demo003.Threads.stop;

/**
 * Thread safe without lock, monitor, and synchronized
 * <p>
 * This class demos usage of atomic variables
 *
 * @author JGD
 * @since 8/28/16
 */
public class AtomicWay {

  private static final Logger log = LoggerFactory.getLogger(AtomicWay.class);
  private final AtomicInteger atomicCount = new AtomicInteger();

  public static void main(String[] params) {
    new AtomicWay().demo();
  }

  private void demo() {
    ExecutorService service = Executors.newWorkStealingPool();
    try {
      IntStream.range(0, 10000).forEach(i -> service.submit(() -> log.info("count={}", atomicCount.incrementAndGet())));
    } finally {
      stop(service);
    }
    log.info("final count={}", atomicCount.get());
  }
}
