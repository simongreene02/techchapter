package com.djd.fun.tachchapter.demo003threads.oldfashion;

import java.util.Collection;

public class Market {

  private final Collection<Runnable> runnables;

  public Market(Collection<Runnable> runnables) {
    this.runnables = runnables;
  }

  public void start() {
    runnables.stream().map(Thread::new).forEach(Thread::start);
  }
}
