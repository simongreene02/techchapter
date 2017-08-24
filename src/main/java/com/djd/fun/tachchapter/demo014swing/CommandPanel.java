package com.djd.fun.tachchapter.demo014swing;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;

public class CommandPanel extends JPanel {

  private final JTextField jTextField;

  public CommandPanel(Document document) {
    super(new BorderLayout());
    this.jTextField = new JTextField(document, "Enter parameter", 1);
    add(jTextField, BorderLayout.CENTER);
  }
}
