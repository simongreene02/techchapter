package com.djd.fun.tachchapter.demo014swing.maze;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

import com.djd.fun.tachchapter.demo014swing.canvas.Abstract2DPanel;
import com.google.common.collect.Sets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmallGame extends Abstract2DPanel {

  private static final Logger log = LoggerFactory.getLogger(SmallGame.class);
  private static final int TILE_SIZE = 20;
  private final Floor floor;
  private final HashSet<Location> remainingTokenLocations;
  private Location currentPlayerLocation;  //currentPlayerLocation

  public SmallGame() {
    this.floor = new FloorFactory().loadFloor("001");
    this.currentPlayerLocation = floor.getOriginalPlayerLocation();
    this.remainingTokenLocations = Sets.newHashSet(floor.getTokenLocations());
    addKeyListener(new MyKeyListener());
  }

  @Override
  protected void paintComponent(Graphics2D g) {
    log.info("repaint");
    // NOTE: This enables KeyListener on JPanel. This has to be called after JFrame is set to visible
    for (int row = 0; row < floor.getNumOfRows(); row++) {
      for (int col = 0; col < floor.getNumOfCols(); col++) {
        paintTile(g, row, col);
      }
    }
    paintPlayer(g);
    requestFocusInWindow();
    if (remainingTokenLocations.isEmpty()) {
      g.setFont(new Font(null, Font.PLAIN, 69));
      g.drawString("Mission Accomplished", 10, 200);
    }
  }

  private void paintTile(Graphics2D g, int row, int col) {
    switch (floor.getTileType(row, col)) {
      case W:
        g.setColor(Color.GRAY);
        break;
      case P:
      case H:
        g.setColor(Color.PINK);
        break;
      case T:
        if (remainingTokenLocations.contains(Location.of(row, col))) {
          g.setColor(Color.YELLOW);
        } else {
          // the token at this location has been taken
          g.setColor(Color.PINK);
        }
        break;
      default:
        g.setColor(Color.RED);
    }
    fillRect(g, row, col);
  }

  private void paintPlayer(Graphics2D g) {
    g.setColor(Color.BLUE);
    fillRect(g, currentPlayerLocation.row, currentPlayerLocation.col);
  }

  private void fillRect(Graphics2D g, int row, int col) {
    g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
  }

  private class MyKeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {
      log.info("keytyped:{}", event);
      Location destination = currentPlayerLocation;
      switch (event.getKeyCode()) {
        case KeyEvent.VK_UP:
          destination = floor.getNorthLocation(currentPlayerLocation);
          break;
        case KeyEvent.VK_DOWN:
          destination = floor.getSouthLocation(currentPlayerLocation);
          break;
        case KeyEvent.VK_LEFT:
          destination = floor.getWestLocation(currentPlayerLocation);
          break;
        case KeyEvent.VK_RIGHT:
          destination = floor.getEastLocation(currentPlayerLocation);
          break;
      }
      if (destination != currentPlayerLocation) {
        currentPlayerLocation = destination;
        // TODO repaint only delta
        remainingTokenLocations.remove(currentPlayerLocation);
        repaint();
      }
    }
  }
}
