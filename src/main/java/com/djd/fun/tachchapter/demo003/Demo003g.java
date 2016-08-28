package com.djd.fun.tachchapter.demo003;

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
 * Part 7) scheduled thread pool executor with {@link Runnable} task/command.
 * <p>
 * Task will be executed multiple times after specified delay and interval delay.
 * {@link ScheduledExecutorService#scheduleAtFixedRate(Runnable, long, long, TimeUnit)}
 *
 * @author JGD
 * @since 8/28/16
 */
public class Demo003g {
  private static final Logger log = LoggerFactory.getLogger(Demo003g.class);

  public static void main(String[] params) {
    log.info("TOP");
    ScheduledExecutorService service = null;
    try {
      // creates ScheduledExecutorService to run task periodically or delay starting execution of tasks
      service = Executors.newScheduledThreadPool(1);

      // scheduleAtFixedRate() does not block main thread, no initially delay to
      // start executing task A. Task A sleeps for 1 seconds. Every 2 second task gets executed.
      // If task execution takes longer than delayRate, scheduleAtFixedRate() does not start
      // another task regardless of pool size. (This seems like scheduleWithFixedDelay behavior)
      // use scheduleWithFixedDelay() This delay starts after completion of task.
      final int initDelay = 0;
      final int delayRate = 2;
      ScheduledFuture<?> future = service.scheduleAtFixedRate(
          makeTask("A", 1), initDelay, delayRate, TimeUnit.SECONDS);
      log.info("delay: {}", future.getDelay(TimeUnit.SECONDS));
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      throw Throwables.propagate(e);
    } finally {
      if (service != null && !service.isTerminated()) {
        service.shutdown();
      }
    }
    log.info("END");
  }

  private static Runnable makeTask(String name, int sleepSeconds) {
    return () -> {
      log.info("[Task {}] starting execution.", name);
      try {
        TimeUnit.SECONDS.sleep(sleepSeconds);
      } catch (InterruptedException e) {
        log.warn("task was interrupted.");
        throw Throwables.propagate(e);
      }
      log.info("[Task {}] completing execution.", name);
    };
  }
}
