package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by acorn on 8/2/17.
 */
public class BullseyeCanvas extends Canvas {

  private static final Color[] RED_BLACK = {Color.BLACK, Color.RED};

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    int x = 100;
    int y = 100;
    int width = 300;
    int height = 300;
    int delta = 1;

    for (int i = 0; i < 7; i++) {
      g.setColor(RED_BLACK[i % 2]);
      g.fillOval(x + delta, y + delta, width - delta * 2, height - delta * 2);
      delta += 15;
    }
  }
}
