package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Created by acorn on 8/2/17.
 */
public class SmileCanvas extends Canvas {

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setColor(Color.YELLOW);
    g2.fillOval(100, 100, 100, 100); // (int x, int y, int width, int height)
    int x = 115;
    int y = 115;
    int width = 70;
    int height = 70;
    /*
     * A startAngle of 0º points horizontally to the right (like the unit circle in math).
     * Positive is a counterclockwise rotation starting at 0º.
     */
    int startAngle = -10;
    int arcAngle = -160;
    g2.setColor(Color.BLACK);
    g2.drawArc(x, y, width, height, startAngle, arcAngle);
  }

}
