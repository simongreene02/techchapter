package com.djd.fun.tachchapter.demo003;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is to demo how to use jdk8 concurrency API
 *
 * @author JGD
 * @since 8/27/16
 */
public class Demo003 {
  private static final Logger log = LoggerFactory.getLogger(Demo003.class);

  public static void main(String[] params) {
    demoSingleThreadExecutor();
  }

  /**
   * Demonstrates how single thread executor works.
   * Submit three tasks to the {@link ExecutorService} to run on worker thread.
   * Tasks are executed in the order of submit.
   *
   * It is very important that executors have to be stopped explicitly.
   */
  public static void demoSingleThreadExecutor() {
    log.info("TOP Demo003: concurrency {}", findCurrentThreadName());
    ExecutorService service = null;
    try {
      service = Executors.newSingleThreadExecutor();
      service.submit(() ->
          log.info("Non-deterministic log. [Task T001] This is executed on a worker thread {}.",
              findCurrentThreadName()));
      service.submit(() ->
          log.info("Non-deterministic log. [Task T002] This is executed on a worker thread {}.",
              findCurrentThreadName()));
      service.submit(() ->
          log.info("Non-deterministic log. [Task T003] This is executed on a worker thread {}.",
              findCurrentThreadName()));
      log.info("MID Demo003: concurrency {}", findCurrentThreadName());
      service.shutdown();
    } catch (RuntimeException e) {
      // shutdown executor
      if (service != null) {
        service.shutdown();
      }
    } finally {
      if (service != null && !service.isShutdown()) {
        // This returns un-executed tasks
        service.shutdownNow();
      }
    }
    log.info("END Demo003: concurrency {}", findCurrentThreadName());
  }

  private static String findCurrentThreadName() {
    return Thread.currentThread().getName();
  }
}
