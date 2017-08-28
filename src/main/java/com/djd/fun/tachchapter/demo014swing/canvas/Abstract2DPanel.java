package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public abstract class Abstract2DPanel extends JPanel implements CommandResponder {

  @Override
  protected final void paintComponent(Graphics g) {
    super.paintComponent(g);
    paintComponent((Graphics2D)g);
  }

  abstract protected void paintComponent(Graphics2D g);

  @Override
  public Component getComponent() {
    return this;
  }

  @Override
  public Document getDocument() {
    return new PlainDocument();
  }

  @Override
  public void init() {

  }
}
