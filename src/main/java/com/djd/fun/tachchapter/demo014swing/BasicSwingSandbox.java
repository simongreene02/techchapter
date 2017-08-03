package com.djd.fun.tachchapter.demo014swing;

import javax.swing.SwingUtilities;

/**
 * Created by acorn on 8/2/17.
 */
public class BasicSwingSandbox {
  public static void main(String[] params) {
    // Use SwingUtilities to run the swing on background thread.
    SwingUtilities.invokeLater(GuiPreper::createAndShowGUI);
  }
}
