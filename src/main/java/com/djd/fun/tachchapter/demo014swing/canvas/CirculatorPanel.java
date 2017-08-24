package com.djd.fun.tachchapter.demo014swing.canvas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.RegEx;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Draw N number of ovals per column.
 *
 * @author acorn
 */
public class CirculatorPanel extends JPanel implements CommandResponder {

  private static final Logger log = LoggerFactory.getLogger(CirculatorPanel.class);
  private static final @RegEx Pattern NUMBERS = Pattern.compile("^\\s*\\d+(\\s+\\d+)*\\s*$");
  private static final Splitter ON_SPACES = Splitter.on(CharMatcher.WHITESPACE).omitEmptyStrings();
  private final Canvas canvas;
  private final Document document;
  private List<Integer> numbers;

  public CirculatorPanel() {
    this.canvas = new MyCanvas();
    this.numbers = Lists.newArrayList();
    this.document = new PlainDocument();
    this.document.addDocumentListener(new MyDocumentListener());
    add(canvas);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    canvas.paint(g);
  }

  @Override
  public Component getComponent() {
    return this;
  }

  @Override
  public Document getDocument() {
    return document;
  }

  private void updateNumbers(DocumentEvent event) {
    log.info("event");
    try {
      Document doc = event.getDocument();
      String inputValue = doc.getText(0, doc.getLength());
      if (NUMBERS.matcher(inputValue).matches()) {
        log.info("inputValue: {}", inputValue);
        numbers = ON_SPACES.splitToList(inputValue).stream()
            .map(Integer::parseInt).collect(Collectors.toList());
        repaint();
      }
    } catch (BadLocationException e) {
      throw new RuntimeException(e);
    }
  }

  private class MyCanvas extends Canvas {

    @Override
    public void paint(Graphics g) {
      super.paint(g);
      g.setColor(Color.BLUE);
      int x = 0;
      int y = 0;
      int w = 20;
      int h = 20;
      for (int col = 1; col <= numbers.size(); col++) {
        for (int row = 0; row < numbers.get(col - 1); row++) {
          g.fillOval(x, y + (row * h), w, h);
        }
        x = (col * w);
      }
    }
  }

  private class MyDocumentListener implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent event) {
      updateNumbers(event);
    }

    @Override
    public void removeUpdate(DocumentEvent event) {
      updateNumbers(event);
    }

    @Override
    public void changedUpdate(DocumentEvent event) {
      updateNumbers(event);
    }
  }
}
