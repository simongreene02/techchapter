package com.djd.fun.tachchapter.demo014swing;

import javax.swing.JFrame;

import com.djd.fun.tachchapter.demo014swing.canvas.GradientCanvas;
import com.djd.fun.tachchapter.demo014swing.canvas.MatchOnCanvas;
import com.djd.fun.tachchapter.demo014swing.canvas.SmileCanvas;

/**
 * Created by acorn on 8/2/17.
 */
public class BasicSwingSandbox {
  public static void main(String[] params) {
    JFrame jFrame = new JFrame(); // Foundation of all graphical components on screen
    jFrame.add(new SmileCanvas()); // Add Canvas instance to draw graphical shapes
    jFrame.setSize(700,500); // Width: 700px and Height: 500px
    jFrame.setVisible(true);
  }
}
