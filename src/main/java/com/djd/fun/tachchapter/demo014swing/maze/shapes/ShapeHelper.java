package com.djd.fun.tachchapter.demo014swing.maze.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.QuadCurve2D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Collection of static helper methods to paint on {@link Graphics2D}
 */
public class ShapeHelper {

  private static final Logger log = LoggerFactory.getLogger(ShapeHelper.class);

  private ShapeHelper() {
    // no instance
  }

  public static void paintWall(Graphics2D g, int row, int col, Color color, int size) {
    g.setColor(color);
    g.fillRect(col * size, row * size, size, size);
  }

  public static void paintPlayer(Graphics2D g, int row, int col, Color color, int size) {
    g.setColor(color);
    g.fillOval(col * size, row * size, size, size);
  }

  public static void paintToken(Graphics2D g, int row, int col, Color color, int size) {
    int smaller = 4;
    int adjustedSize = size - (smaller * 2);
    g.setColor(color);
    g.fillOval(col * size + smaller, row * size + smaller, adjustedSize, adjustedSize);
  }

  public static void paintEnemy(Graphics2D g, int row, int col, Color color, int size) {
    g.setColor(color);
    g.fill(StarPolygon.with(row, col, size));
  }

  public static void paintGem(Graphics2D g, int row, int col, Color color, int size) {
    g.setColor(color);
    g.fill(Diamond.with(row, col, size));
  }

  public static void paintUpstairs(Graphics2D g, int row, int col, Color color, int size) {
    int smaller = 2;
    int adjustedRectSize = size - (smaller * 2);
    g.setColor(color);
    g.drawRect(col * size + smaller, row * size + smaller, adjustedRectSize, adjustedRectSize);
    g.fill(Triangle.up(row, col, size));
  }

  public static void paintDownstairs(Graphics2D g, int row, int col, Color color, int size) {
    int smaller = 2;
    int adjustedRectSize = size - (smaller * 2);
    g.setColor(color);
    g.drawRect(col * size + smaller, row * size + smaller, adjustedRectSize, adjustedRectSize);
    g.fill(Triangle.down(row, col, size));
  }
}
