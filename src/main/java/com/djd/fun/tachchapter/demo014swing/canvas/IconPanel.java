package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import com.google.common.io.Resources;

public class IconPanel extends JPanel implements CommandResponder {

  private BufferedImage image;

  public IconPanel() {
    try {
      image = ImageIO.read(Resources.getResource("nz.jpg"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Image scaledImage = image.getScaledInstance(getWidth(),getHeight(), Image.SCALE_FAST);
    ((Graphics2D)g).drawImage(scaledImage, null, null);
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
