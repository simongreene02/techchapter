package com.djd.fun.tachchapter.demo003;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.djd.fun.tachchapter.demo003.Threads.findCurrentThreadName;

/**
 * This class is to demo how to use jdk8 concurrency API
 * <p>
 * Part 4) cached thread pool executor with {@link java.util.concurrent.Callable} tasks
 * using {@link ExecutorService#invokeAny(Collection)}.
 * <p>
 * Note: cached thread pool size dynamically changes depends on thread availability.
 * <p>
 * {@link ExecutorService#invokeAny(Collection)} will block main thread until first {@link Callable}
 * is completed.
 *
 * From {@link Executors} javadoc
 * These pools will typically improve the performance
 * of programs that execute many short-lived asynchronous tasks.
 * Threads that have
 * not been used for sixty seconds are terminated and removed from
 * the cache.
 *
 * @author JGD
 * @since 8/28/16
 */
public class Demo003d {
  private static final Logger log = LoggerFactory.getLogger(Demo003d.class);

  public static void main(String[] params) {
    log.info("TOP Demo003d");
    ExecutorService service = null;
    try {
      // Create dynamically sized thread pool
      service = Executors.newCachedThreadPool();

      // This invokeAny call blocks current/main thread until first task returns.
      String result =
          service.invokeAny(ImmutableList.of(makeTask("A", 3), makeTask("B", 2), makeTask("C", 1)));
      log.info("Winner is Task {} ", result);
    } catch (InterruptedException | ExecutionException e) {
      log.warn("task was interrupted");
      throw Throwables.propagate(e);
    } finally {
      if (service != null && !service.isTerminated()) {
        List<Runnable> remainingTasks = service.shutdownNow();
        log.info("remaining task count: {}", remainingTasks.size());
      }
    }
  }

  private static Callable<String> makeTask(String name, int sleepSeconds) {
    return () -> {
      log.info("Non-deterministic log. [Task {}] This is executed on a worker thread.",
          name);
      TimeUnit.SECONDS.sleep(sleepSeconds);
      return name;
    };
  }
}
