package com.djd.fun.tachchapter.demo014swing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.djd.fun.tachchapter.demo014swing.canvas.SmileCanvas;

/**
 * Created by acorn on 8/2/17.
 */
public class BasicSwingSandbox {
  public static void main(String[] params) {
    // Use SwingUtilities to run the swing on background thread.
    SwingUtilities.invokeLater(() -> {
      JFrame jFrame = new JFrame(); // Foundation of all graphical components on screen
      jFrame.add(new SmileCanvas()); // Add Canvas instance to draw graphical shapes
      jFrame.setSize(700, 500); // Width: 700px and Height: 500px
      jFrame.setVisible(true);
    });
  }
}
