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
 * Part 4) cached thread pool executor with {@link java.util.concurrent.Callable} tasks
 * using {@link ExecutorService#invokeAny(Collection)}.
 * <p>
 * Note: cached thread pool size dynamically changes depends on thread availability.
 * <p>
 * {@link ExecutorService#invokeAny(Collection)} will block main thread until first {@link Callable}
 * is completed.
 * <p>
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
      // Tasks that have not completed are cancelled.
      String result =
          service.invokeAny(ImmutableList.of(makeTask("A", 13), makeTask("B", 1), makeTask("C", 17)));
      log.info("Winner is Task {} ", result);
    } catch (InterruptedException | ExecutionException e) {
      log.warn("task was interrupted");
      throw Throwables.propagate(e);
    } finally {
      if (service != null && !service.isTerminated()) {
        service.shutdown();
      }
    }
    log.info("END Demo003d");
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
