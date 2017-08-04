package com.djd.fun.tachchapter.demo014swing;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import com.djd.fun.tachchapter.demo014swing.canvas.BullseyeCanvas;
import com.djd.fun.tachchapter.demo014swing.canvas.GradientCanvas;
import com.djd.fun.tachchapter.demo014swing.canvas.MatchOnCanvas;
import com.djd.fun.tachchapter.demo014swing.canvas.SmileCanvas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by acorn on 8/3/17.
 */
public class GuiPreper {

  private static final Logger log = LoggerFactory.getLogger(GuiPreper.class);

  public static void createAndShowGUI() {
    JFrame jFrame = new JFrame("Demo 014"); // Foundation of all graphical components on screen
    jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jFrame.setJMenuBar(new MyMenuBar()); // add Menu
    jFrame.setContentPane(new MyPanel());
    jFrame.pack();
    jFrame.setVisible(true);
  }
}
