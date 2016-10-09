package com.djd.fun.poc.geo.event;

/**
 * Just a marker event to indicate a terminal signal in {@link com.google.common.eventbus.EventBus}
 * @author JGD
 * @since 10/8/16
 */
public class TerminalSignal {
  public static final TerminalSignal TERMINAL_SIGNAL = new TerminalSignal();
  private TerminalSignal() {}
}
