package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.regex.Pattern;

import javax.annotation.RegEx;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by acorn on 8/2/17.
 */
public class BullseyeCanvas extends Canvas implements CommandResponder {

  private static final Logger log = LoggerFactory.getLogger(BullseyeCanvas.class);
  private static final Color[] RED_BLACK = {Color.BLACK, Color.RED};
  private static final @RegEx Pattern TWO_DIGITS = Pattern.compile("[0-9]{1,2}");
  private int ringCount = 7;

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    int x = 100;
    int y = 100;
    int width = 300;
    int height = 300;
    int delta = 1;

    for (int i = 0; i < ringCount; i++) {
      g.setColor(RED_BLACK[i % 2]);
      g.fillOval(x + delta, y + delta, width - delta * 2, height - delta * 2);
      delta += 15;
    }
  }

  @Override
  public Component getComponent() {
    return this;
  }

  @Override
  public Document getDocument() {
    Document document = new PlainDocument();
    document.addDocumentListener(new MyDocumentListener());
    return document;
  }

  private class MyDocumentListener implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent event) {
      log.info("insertUpdate {}", event);
      Document doc = event.getDocument();
      try {
        String inputValue = doc.getText(0, doc.getLength());
        if (TWO_DIGITS.matcher(inputValue).matches()) {
          log.info("inputValue: {}", inputValue);
          ringCount = Integer.parseInt(inputValue);
          repaint();
        }
      } catch (BadLocationException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
      log.info("removeUpdate {}", e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
      log.info("changedUpdate {}", e);
    }
  }
}
