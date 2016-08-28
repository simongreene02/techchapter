package com.djd.fun.tachchapter.demo003;


import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.djd.fun.tachchapter.demo003.Threads.findCurrentThreadName;

/**
 * This class is to demo how to use jdk8 concurrency API
 * <p>
 * Part 2) single thread executor with {@link java.util.concurrent.Callable} tasks
 *
 * We can do differently using {@link ExecutorService#invokeAll(Collection)}. See Demo003c
 *
 * @author JGD
 * @since 8/27/16
 */
public class Demo003b {
  private static final Logger log = LoggerFactory.getLogger(Demo003b.class);

  public static void main(String[] params) {
    log.info("TOP Demo003b {}", findCurrentThreadName());
    List<Future<String>> futures = Lists.newArrayList();
    ExecutorService service = null;
    try {
      // same as Executors.newSingleThreadExecutor();
      service = Executors.newFixedThreadPool(1);
      // task is Callable<String>
      futures.add(service.submit(() -> {
        log.info("Non-deterministic log. [Task T001] This is executed on a worker thread {}.",
            findCurrentThreadName());
        return "Task 1";
      }));
      futures.add(service.submit(() -> {
        log.info("Non-deterministic log. [Task T002] This is executed on a worker thread {}.",
            findCurrentThreadName());
        return "Task 2";
      }));
      futures.add(service.submit(() -> {
        log.info("Non-deterministic log. [Task T003] This is executed on a worker thread {}.",
            findCurrentThreadName());
        return "Task 3";
      }));
      log.info("MID Demo003b {}", findCurrentThreadName());
    } finally {
      if (service != null && !service.isTerminated()) {
        service.shutdownNow();
      }
    }
    futures.stream().map(future -> {
      try {
        return future.get();
      } catch (InterruptedException | ExecutionException e) {
        throw Throwables.propagate(e);
      }
    }).forEach(result -> log.info("result: {}", result));
    log.info("END Demo003b {}", findCurrentThreadName());
  }
}
