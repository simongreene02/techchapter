package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gradient2JPanel extends JPanel {

  private static final Logger log = LoggerFactory.getLogger(Gradient2JPanel.class);

  private final int width;
  private final int height;
  private final BufferedImage bufferedImage;

  public Gradient2JPanel() {
    this(200, 200);
  }

  public Gradient2JPanel(int width, int height) {
    this.width = width;
    this.height = height;
    this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    initImage();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(width, height);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.drawImage(bufferedImage, null, null);
  }

  private void initImage() {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rg = colorValue(x + y);
        bufferedImage.setRGB(x, y, new Color(rg, rg, 0).getRGB());
      }
    }
  }

  /**
   * @param value any value
   * @return 0 if value is less than 0. 255 if the value if greater than 255. Otherwise value.
   */
  private static int colorValue(int value) {
    if (value < 0) {
      return 0;
    }
    return Math.min(value, 255);
  }
}
