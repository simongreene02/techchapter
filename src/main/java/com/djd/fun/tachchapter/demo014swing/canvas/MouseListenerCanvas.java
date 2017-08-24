package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.security.cert.CRLSelector;

import javax.swing.event.MouseInputAdapter;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class demonstrates use of MouseListener.
 */
public class MouseListenerCanvas extends Canvas implements CommandResponder {
  private static final Logger log = LoggerFactory.getLogger(MouseListenerCanvas.class);
  private static final Color[] COLORS = {Color.RED, Color.GREEN, Color.BLUE};
  private int firstColorIndex = 0;
  private int secondColorIndex = 1;

  public MouseListenerCanvas() {
    super.addMouseListener(new MouseInputCallback());
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

  /**
   * Adjust rawIndex so that if the index >= COLORS.length, wrap it.
   *
   * @param rawIndex
   * @return adjusted color index
   */
  private static int adjustColorIndex(int rawIndex) {
    return rawIndex < COLORS.length ? rawIndex : rawIndex - COLORS.length;
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
   * callback class for mouse event
   * <p>
   * On left-click change first color index. On right-click change second color index.
   */
  private class MouseInputCallback extends MouseInputAdapter {

    @Override
    public void mouseClicked(MouseEvent event) {
      int buttonId = event.getButton();
      log.info("button #:{}", buttonId);
      switch (buttonId) {
        case MouseEvent.BUTTON1:
          firstColorIndex = adjustColorIndex(firstColorIndex + 1);
          break;
        case MouseEvent.BUTTON2:
          secondColorIndex = adjustColorIndex(secondColorIndex + 1);
          break;
      }
      repaint();
      revalidate();
    }
  }
}
