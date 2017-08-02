package com.djd.fun.tachchapter.demo003threads;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is to demo how to use jdk8 concurrency API
 * <p>
 * Part 5) jdk8's work-stealing thread pool executor with {@link java.util.concurrent.Callable} tasks
 * using {@link ExecutorService#invokeAny(Collection)}.
 * <p>
 * Note: work-stealing thread pool utilize computer's available cores to maximize parallelism.
 * <p>
 * {@link ExecutorService#invokeAny(Collection)} will block main thread until first {@link Callable}
 * is completed.
 * <p>
 *
 * @author JGD
 * @since 8/28/16
 */
public class Demo003e {
  private static final Logger log = LoggerFactory.getLogger(Demo003e.class);

  public static void main(String[] params) {
    log.info("TOP Demo003e");
    ExecutorService service = null;
    try {
      // creates ForkJoinPool that utilize the all available cores
      service = Executors.newWorkStealingPool();

      // As soon as one task is done rest of tasks are cancelled.
      String result = service.invokeAny(ImmutableList.of(
          makeTask("A", 12), makeTask("B", 15), makeTask("C", 13), makeTask("D", 1), makeTask("E", 1)));
      log.info("Winner is Task {}", result);
    } catch (InterruptedException | ExecutionException e) {
      log.warn("task is interrupted");
      throw Throwables.propagate(e);
    } finally {
      if (service != null && !service.isTerminated()) {
        service.shutdown();
      }
    }
    log.info("END Demo003e");
  }

  private static Callable<String> makeTask(String name, int sleepSeconds) {
    return () -> {
      log.info("[Task {}] starting execution.", name);
      TimeUnit.SECONDS.sleep(sleepSeconds);
      log.info("[Task {}] completing execution.", name);
      return name;
    };
  }
}
