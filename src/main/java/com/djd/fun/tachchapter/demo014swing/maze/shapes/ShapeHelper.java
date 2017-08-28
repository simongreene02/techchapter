package com.djd.fun.tachchapter.demo014swing.maze.shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * Collection of static helper methods
 */
public class ShapeHelper {
  private ShapeHelper() {
    // no instance
  }

  public static void fillRect(Graphics2D g, int row, int col, Color color, int size) {
    g.setColor(color);
    g.fillRect(col * size, row * size, size, size);
  }

  public static void fillOval(Graphics2D g, int row, int col, Color color, int size) {
    g.setColor(color);
    g.fillOval(col * size, row * size, size, size);
  }

  public static void fillSmallOval(Graphics2D g, int row, int col, Color color, int size) {
    int smaller = 4;
    int adjustedSize = size - (smaller * 2);
    g.setColor(color);
    g.fillOval(col * size + smaller, row * size + smaller, adjustedSize, adjustedSize);
  }

  public static void fillPolygon(Graphics2D g, int row, int col, Color color, int size) {
    int delta = size / 2;
    g.setColor(color);
    g.fill(new StarPolygon(col * size + delta, row * size + delta, delta, delta / 2));
  }

  public static void fillDiamond(Graphics2D g, int row, int col, Color color, int size) {
    g.setColor(color);
    g.fill(new Diamond(row, col, size));
  }

  public static void fillTriangleUp(Graphics2D g, int row, int col, Color color, int size) {
    int smaller = 2;
    int adjustedRectSize = size - (smaller * 2);
    g.setColor(color);
    g.drawRect(col * size + smaller, row * size + smaller, adjustedRectSize, adjustedRectSize);
    g.fill(Triangle.up(row, col, size));
  }

  public static void fillTriangleDown(Graphics2D g, int row, int col, Color color, int size) {
    int smaller = 2;
    int adjustedRectSize = size - (smaller * 2);
    g.setColor(color);
    g.drawRect(col * size + smaller, row * size + smaller, adjustedRectSize, adjustedRectSize);
    g.fill(Triangle.down(row, col, size));
  }

  public static void drawString(Graphics2D g, int x, int y, String message, int size, Color color) {
    g.setFont(new Font(null, Font.PLAIN, size));
    g.setColor(color);
    g.drawString(message, x, y);
  }
}
