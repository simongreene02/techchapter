package com.djd.fun.tachchapter.demo003threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Throwables;
import com.sun.istack.internal.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a collection of static utility methods related to {@link Thread}
 * @author JGD
 * @since 8/27/16
 */
public class Threads {
  public static String findCurrentThreadName() {
    return Thread.currentThread().getName();
  }

  private static final Logger log = LoggerFactory.getLogger(Threads.class);

  /**
   * Put current thread to sleep for the specified duration.
   *
   * @param seconds to sleep
   * @throws RuntimeException wrapper of {@lnk  InterruptedException}
   */
  public static void sleep(long seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      throw Throwables.propagate(e);
    }
  }

  /**
   * Helper method to stop specified service.
   *
   * @param service {@link ExecutorService} service to be stopped
   */
  public static void stop(@Nullable ExecutorService service) {
    if (service != null && !service.isTerminated()) {
      try {
        service.shutdown();
        service.awaitTermination(5, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        throw Throwables.propagate(e);
      } finally {
        if (!service.isTerminated()) {
          log.warn("cancel unprocessed tasks");
        }
        service.shutdownNow();
      }
    }
  }
}
