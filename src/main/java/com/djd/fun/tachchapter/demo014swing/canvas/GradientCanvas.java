package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Created by acorn on 8/2/17.
 */
public class GradientCanvas extends Canvas {

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2 = (Graphics2D)g;
    Point topLeft = new Point(100, 100);
    Point bottomRight = new Point(300, 300);
    GradientPaint paint = new GradientPaint(topLeft, Color.BLACK, bottomRight, Color.YELLOW);
    g2.setPaint(paint);
    g2.fillRect(100, 100, 200, 200); // (int x, int y, int width, int height)
  }
}
