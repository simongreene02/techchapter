package com.djd.fun.tachchapter.demo003threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Throwables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is to demo how to use jdk8 concurrency API
 * <p>
 * Part 6) scheduled thread pool executor with {@link Callable} task.
 * <p>
 * Task will be executed only once after specified delay.
 *
 * @author JGD
 * @since 8/28/16
 */
public class Demo003f {
  private static final Logger log = LoggerFactory.getLogger(Demo003f.class);

  public static void main(String[] params) {
    log.info("TOP");
    ScheduledExecutorService service = null;
    try {
      // creates ScheduledExecutorService to run task periodically or delay starting execution of tasks
      service = Executors.newScheduledThreadPool(1);

      // schedule() does not block main thread, delay 5 seconds to start executing task A.
      // task A sleeps for 3 seconds.
      ScheduledFuture<String> future = service.schedule(makeTask("A", 3), 5, TimeUnit.SECONDS);

      // blocking main thread for 5 + 3 = 8 seconds.
      log.info("Task{} is finished.", future.get());
    } catch (InterruptedException | ExecutionException e) {
      log.warn("task is interrupted");
      throw Throwables.propagate(e);
    } finally {
      if (service != null && !service.isTerminated()) {
        service.shutdown();
      }
    }
    log.info("END");
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
