package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by acorn on 8/2/17.
 */
public class BullseyeCanvas extends Canvas {

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    int x = 100;
    int y = 100;
    int width = 300;
    int height = 300;
    int delta = 10;
    g.drawOval(x, y, width, height);
    g.fillOval(x + delta, y + delta, width - delta * 2, height - delta * 2);
    g.setColor(Color.RED);
    delta += 10;
    g.fillOval(x + delta, y + delta, width - delta * 2, height - delta * 2);

    // TODO try above with loop
  }
}
