package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.awt.Font.PLAIN;

/**
 * Demo of Timer based {@link ActionListener} and {@link MouseInputAdapter}
 * One of three shapes appears every 500 milliseconds. Stop the loop by clicking on the green circle.
 */
public class Animation extends JPanel implements CommandResponder {
  enum ShapeType {
    CIRCLE, SQUARE, TRIANGLE
  }

  private static final Logger log = LoggerFactory.getLogger(Animation.class);
  private static final int SIZE = 80;
  private static final ShapeType[] SHAPE_TYPES = {ShapeType.CIRCLE, ShapeType.SQUARE, ShapeType.TRIANGLE};
  private static final int DELAY_MILLISECONDS = 500;
  private final Random random;
  private final Timer timer;
  private boolean done = false;

  private ShapeType currentShape = ShapeType.CIRCLE;

  public Animation() {
    this(new Random());
  }

  public Animation(Random random) {
    this.random = random;
    this.timer = new Timer(DELAY_MILLISECONDS, new MyActionListener());
    this.timer.start();
    addMouseListener(new MouseEventListener());
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.GRAY);
    g.fillRect(0, 0, 300, 100);
    switch (currentShape) {
      case CIRCLE:
        g.setColor(Color.GREEN);
        g.fillOval(210, 10, SIZE, SIZE);
        break;
      case TRIANGLE:
        g.setColor(Color.PINK);
        g.fillPolygon(
            new int[]{10, 10 + SIZE, (10 + SIZE) / 2},
            new int[]{10 + SIZE, 10 + SIZE, 10},
            3);
        break;
      case SQUARE:
        g.setColor(Color.YELLOW);
        g.fillRect(110, 10, SIZE, SIZE);
        break;
    }
    if (done) {
      g.setColor(Color.BLACK);
      g.drawRect(0, 120, 185, 50);
      g.setFont(new Font(null, PLAIN, 48));
      g.drawString("Winner!", 10, 160);
    }
  }

  @Override
  public Component getComponent() {
    return this;
  }

  @Override
  public Document getDocument() {
    return new PlainDocument();
  }

  /**
   * Defines callback method which gets called by the timer every DELAY_MILLISECONDS
   * Randomly pick one of the predefined shape and repaint the panel
   */
  private class MyActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      log.info("timer is up");
      currentShape = SHAPE_TYPES[random.nextInt(3)];
      repaint();
    }
  }

  /**
   * When the mouse is clicked check the x,y coordinate. If it is on the circle, stop the timer and
   * display the message.
   */
  private class MouseEventListener extends MouseInputAdapter {
    private static final int CENTER_X = 210 + (SIZE / 2);
    private static final int CENTER_Y = 10 + (SIZE / 2);
    private static final int RADIUS = SIZE / 2;

    @Override
    public void mouseClicked(MouseEvent event) {
      if (currentShape != ShapeType.CIRCLE) {
        return;
      }
      int x = event.getX();
      int y = event.getY();
      if (x < CENTER_X - RADIUS || x > CENTER_X + RADIUS ||
          y < CENTER_Y - RADIUS || y > CENTER_Y + RADIUS) {
        log.info("outside of circle");
        return;
      }
      double dx = CENTER_X - x;
      double dy = CENTER_Y - y;
      dx *= dx;
      dy *= dy;
      double distanceSquared = dx + dy;
      double radiusSquared = RADIUS * RADIUS;
      if (distanceSquared <= radiusSquared) {
        timer.stop();
        done = true;
        repaint();
      }
    }
  }
}
