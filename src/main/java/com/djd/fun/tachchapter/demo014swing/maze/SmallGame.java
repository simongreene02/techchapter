package com.djd.fun.tachchapter.demo014swing.maze;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Set;

import javax.swing.Timer;

import com.djd.fun.tachchapter.demo014swing.canvas.Abstract2DPanel;
import com.google.common.collect.Sets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmallGame extends Abstract2DPanel {

  private static final Logger log = LoggerFactory.getLogger(SmallGame.class);
  private static final int[] DIRECTIONS = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
  private static final int TILE_SIZE = 25;
  private final KeyListener keyListener;
  private final ActionListener actionListener;
  private final Random random;
  private final Timer timer;
  private final Floor floor;
  private final Set<Location> remainingTokenLocations;
  private final Set<Location> enemyLocations;
  private Location currentPlayerLocation;

  public SmallGame() {
    this.floor = new FloorFactory().loadFloor("001");
    this.currentPlayerLocation = floor.getOriginalPlayerLocation();
    this.remainingTokenLocations = Sets.newHashSet(floor.getTokenLocations());
    this.enemyLocations = Sets.newConcurrentHashSet(floor.getEnemyLocations());
    this.keyListener = new MyKeyListener();
    this.actionListener = new AnimateEnemy();
    this.random = new Random(0);
    this.timer = new Timer(871, actionListener);
    this.timer.start();
    addKeyListener(keyListener);
  }

  @Override
  protected void paintComponent(Graphics2D g) {
    paintTiles(g);
    paintPlayer(g);
    paintEnemies(g);
    if (enemyLocations.contains(currentPlayerLocation)) {
      timer.stop();
      removeKeyListener(keyListener);
      g.setFont(new Font(null, Font.PLAIN, 69));
      g.setColor(Color.RED);
      g.drawString("Mission Failed", 50, 200);
    }
    if (remainingTokenLocations.isEmpty()) {
      g.setFont(new Font(null, Font.PLAIN, 69));
      g.setColor(Color.GREEN);
      g.drawString("Mission Accomplished", 10, 200);
    }
    requestFocusInWindow(); // NOTE: This enables KeyListener on JPanel. This has to be called after JFrame is set to visible
  }

  private void paintTiles(Graphics2D g) {
    for (int row = 0; row < floor.getNumOfRows(); row++) {
      for (int col = 0; col < floor.getNumOfCols(); col++) {
        fillRect(g, row, col, Color.PINK);
        switch (floor.getTileType(row, col)) {
          case W:
            fillRect(g, row, col, Color.GRAY);
            break;
          case T:
            if (remainingTokenLocations.contains(Location.of(row, col))) {
              fillOval(g, row, col, Color.YELLOW);
            }
            break;
        }
      }
    }
  }

  private void paintPlayer(Graphics2D g) {
    fillOval(g, currentPlayerLocation.row, currentPlayerLocation.col, Color.BLUE);
  }

  private void paintEnemies(Graphics2D g) {
    enemyLocations.forEach(location -> fillOval(g, location.row, location.col, Color.RED));
  }

  private static void fillRect(Graphics2D g, int row, int col, Color color) {
    g.setColor(color);
    g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
  }

  private static void fillOval(Graphics2D g, int row, int col, Color color) {
    g.setColor(color);
    g.fillOval(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
  }

  /**
   * @param directionCode
   * @param location
   * @return destination {@link Location} or location
   */
  private Location getTargetLocation(int directionCode, Location location) {
    switch (directionCode) {
      case KeyEvent.VK_UP:
        return floor.getNorthLocation(location);
      case KeyEvent.VK_DOWN:
        return floor.getSouthLocation(location);
      case KeyEvent.VK_LEFT:
        return floor.getWestLocation(location);
      case KeyEvent.VK_RIGHT:
        return floor.getEastLocation(location);
      default:
        return location;
    }
  }

  private class MyKeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {
      log.info("keytyped:{}", event);
      Location destination = getTargetLocation(event.getKeyCode(), currentPlayerLocation);

      if (!destination.equals(currentPlayerLocation)) {
        currentPlayerLocation = destination;
        // TODO repaint only delta
        remainingTokenLocations.remove(currentPlayerLocation);
        repaint();
      }
    }
  }

  private class AnimateEnemy implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      Set<Location> destinations = Sets.newHashSet();
      // Move locations of enemies
      for (Location location : enemyLocations) {
        Location destination = getTargetLocation(
            DIRECTIONS[random.nextInt(DIRECTIONS.length)], location);
        if (destinations.contains(destination) || enemyLocations.contains(destination)) {
          // Enemy cannot move to a location another enemy is already on.
          destinations.add(location);
        } else {
          destinations.add(destination);
        }
      }
      enemyLocations.clear();
      enemyLocations.addAll(destinations);
      repaint();
    }
  }
}
