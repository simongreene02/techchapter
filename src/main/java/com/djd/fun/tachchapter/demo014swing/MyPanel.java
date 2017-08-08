package com.djd.fun.tachchapter.demo014swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JPanel;

import com.djd.fun.tachchapter.demo014swing.canvas.BullseyeCanvas;
import com.djd.fun.tachchapter.demo014swing.canvas.Gradient2JPanel;
import com.djd.fun.tachchapter.demo014swing.canvas.GradientCanvas;
import com.djd.fun.tachchapter.demo014swing.canvas.MatchOnCanvas;
import com.djd.fun.tachchapter.demo014swing.canvas.SmileCanvas;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.awt.BorderLayout.CENTER;

public class MyPanel extends JPanel implements ActionListener {

  private static final Logger log = LoggerFactory.getLogger(MyPanel.class);
  private final ImmutableMap<String, Component> components;
  private final ImmutableSet<String> componentNames;

  public MyPanel() {
    super(new BorderLayout());
    this.components = ImmutableMap.of(
        "GradientCanvas", new GradientCanvas(),
        "Gradient2JPanel", new Gradient2JPanel(),
        "BullseyeCanvas", new BullseyeCanvas(),
        "MatchOnCanvas", new MatchOnCanvas(),
        "SmileCanvas", new SmileCanvas());
    this.componentNames = ImmutableSortedSet.copyOf(components.keySet());
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(700, 500);
  }
  /**
   * Add component to this JPanel
   *
   * @param event
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    log.info("action command: " + event.getActionCommand());
    removeAll(); // remove previous component from this panel
    add(components.get(event.getActionCommand()), CENTER); // add selected component to this panel
    revalidate(); // refresh the view
  }

  /**
   * @return all available component names
   */
  public Collection<String> getComponentNames() {
    return componentNames;
  }
}
