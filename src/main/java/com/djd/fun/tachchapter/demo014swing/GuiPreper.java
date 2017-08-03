package com.djd.fun.tachchapter.demo014swing;

import javax.swing.JFrame;

import com.djd.fun.tachchapter.demo014swing.canvas.BullseyeCanvas;
import com.djd.fun.tachchapter.demo014swing.canvas.GradientCanvas;
import com.djd.fun.tachchapter.demo014swing.canvas.MatchOnCanvas;
import com.djd.fun.tachchapter.demo014swing.canvas.SmileCanvas;

/**
 * Created by acorn on 8/3/17.
 */
public class GuiPreper {
  public static void createAndShowGUI() {
    JFrame jFrame = new JFrame(); // Foundation of all graphical components on screen
    jFrame.add(new GradientCanvas());
    jFrame.add(new BullseyeCanvas());
    jFrame.add(new MatchOnCanvas());
    jFrame.add(new SmileCanvas()); // Add Canvas instance to draw graphical shapes
    jFrame.setSize(700, 500); // Width: 700px and Height: 500px
    jFrame.setVisible(true);
  }
}
