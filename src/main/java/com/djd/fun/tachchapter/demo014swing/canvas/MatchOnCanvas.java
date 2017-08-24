package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * Created by acorn on 8/2/17.
 */
public class MatchOnCanvas extends Canvas implements CommandResponder {

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.drawRect(70, 80, 70, 140); // (int x, int y, int width, int height)
    g2.setColor(Color.RED);
    g2.fillRect(100, 100, 10, 100); // (int x, int y, int width, int height)
    g2.setColor(Color.BLUE);
    g2.fillOval(98, 98, 14, 14); // (int x, int y, int width, int height)
  }

  @Override
  public Component getComponent() {
    return this;
  }

  @Override
  public Document getDocument() {
    return new PlainDocument();
  }
}
