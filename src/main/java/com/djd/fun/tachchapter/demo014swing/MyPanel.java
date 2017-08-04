package com.djd.fun.tachchapter.demo014swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.djd.fun.tachchapter.demo014swing.canvas.Gradient2JPanel;
import com.djd.fun.tachchapter.demo014swing.canvas.GradientCanvas;

import static java.awt.BorderLayout.CENTER;

public class MyPanel extends JPanel {

  public MyPanel() {
    super(new BorderLayout());
    add(new Gradient2JPanel(), CENTER);
//    add(new BullseyeCanvas(), CENTER);
//    add(new MatchOnCanvas(), CENTER);
//    add(new SmileCanvas(), CENTER); // Add Canvas instance to draw graphical shapes
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(700, 500);
  }
}
