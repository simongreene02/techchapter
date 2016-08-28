package com.djd.fun.tachchapter.demo003;


import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.djd.fun.tachchapter.demo003.Threads.findCurrentThreadName;

/**
 * This class is to demo how to use jdk8 concurrency API
 * <p>
 * Part 3) single thread executor with {@link java.util.concurrent.Callable} tasks
 * using {@link ExecutorService#invokeAll(Collection)}.
 *
 * @author JGD
 * @since 8/27/16
 */
public class Demo003c {
  private static final Logger log = LoggerFactory.getLogger(Demo003c.class);

  public static void main(String[] params) {
    log.info("TOP Demo003c {}", findCurrentThreadName());
    ExecutorService service = null;
    try {
      // same as Executors.newSingleThreadExecutor();
      service = Executors.newFixedThreadPool(1);
      service
          .invokeAll(ImmutableList.of(createTask("001"), createTask("002"), createTask("003")))
          .stream()
          .map(future -> {
            try {
              // This call blocks main thread.
              return future.get();
            } catch (InterruptedException e) {
              throw Throwables.propagate(e);
            } catch (ExecutionException e) {
              throw Throwables.propagate(e);
            }
          })
          .forEach(result -> log.info("result: {}", result));
      log.info("MID Demo003c {}", findCurrentThreadName());
    } catch (InterruptedException e) {
      log.error("Task execution was interrupted.", e);
      throw Throwables.propagate(e);
    } finally {
      if (service != null && !service.isTerminated()) {
        service.shutdownNow();
      }
    }
    log.info("END Demo003c {}", findCurrentThreadName());
  }

  private static Callable<String> createTask(String taskName) {
    return new CallableTask("Task" + taskName);
  }

  private static class CallableTask implements Callable<String> {

    private final String name;

    public CallableTask(String name) {
      this.name = name;
    }

    @Override public String call() throws Exception {
      log.info("Non-deterministic log. [Task {}] This is executed on a worker thread {}.",
          name, findCurrentThreadName());
      return name;
    }
  }
}
