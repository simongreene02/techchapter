package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by acorn on 8/2/17.
 */
public class GradientCanvas extends Canvas implements ActionListener, CommandResponder {
  private static final Logger log = LoggerFactory.getLogger(GradientCanvas.class);
  private static final Color [] COLORS = {Color.RED, Color.BLUE, Color.BLACK, Color.YELLOW, Color.GREEN};
  private final Timer timer;
  private int firstColorIndex = 2;
  private int secondColorIndex = 3;

  public GradientCanvas() {
    this.timer = new Timer(1000, this);
    this.timer.start();
  }
  @Override
  public void paint(Graphics g) {
    log.info("paint called.");
    super.paint(g);
    Graphics2D g2 = (Graphics2D)g;
    Point topLeft = new Point(100, 100);
    Point bottomRight = new Point(300, 300);
    GradientPaint paint = new GradientPaint(
        topLeft, COLORS[firstColorIndex],
        bottomRight, COLORS[secondColorIndex]);
    g2.setPaint(paint);
    g2.fillRect(100, 100, 200, 200); // (int x, int y, int width, int height)
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    firstColorIndex = secondColorIndex;
    secondColorIndex = (int)(event.getWhen() % COLORS.length);
    repaint();
    revalidate();
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
