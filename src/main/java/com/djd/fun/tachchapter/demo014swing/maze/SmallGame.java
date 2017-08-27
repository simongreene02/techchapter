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
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.Timer;

import com.djd.fun.tachchapter.demo014swing.canvas.Abstract2DPanel;
import com.djd.fun.tachchapter.demo014swing.maze.shapes.StarPolygon;
import com.djd.fun.tachchapter.demo014swing.maze.states.FloorStates;
import com.google.common.collect.Sets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmallGame extends Abstract2DPanel {

  private static final Logger log = LoggerFactory.getLogger(SmallGame.class);
  private static final int[] DIRECTIONS = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
  private static final int TILE_SIZE = 20; // Note Use even number to avoid loss of decimal
  private final KeyListener keyListener;
  private final ActionListener animateEnemy;
  private final ActionListener invincibleListener;
  private final Random random;
  private final Timer invincibleTimer;
  private final Timer emenyTimer;
  private final AtomicBoolean invincible;
  private final FloorStates floorStates;
  private Location currentPlayerLocation;

  public SmallGame() {
    this.floorStates = new FloorStates();
    this.invincible = new AtomicBoolean();
    this.currentPlayerLocation = floorStates.getOriginalPlayerLocation();
    this.keyListener = new MyKeyListener();
    this.animateEnemy = new AnimateEnemy();
    this.invincibleListener = new InvincibleMode();
    this.random = new Random(0);
    this.invincibleTimer = new Timer(5000, invincibleListener);
    this.invincibleTimer.setRepeats(false);
    this.emenyTimer = new Timer(777, animateEnemy);
    this.emenyTimer.start();
    addKeyListener(keyListener);
  }

  @Override
  protected void paintComponent(Graphics2D g) {
    paintTiles(g);
    paintPlayer(g);
    paintEnemies(g);
    if (floorStates.isEnemyAt(currentPlayerLocation)) {
      if (invincible.get()) {
        log.info("player invincible");
        // vanish enemy
        floorStates.removeEnemyAt(currentPlayerLocation);
      } else {
        log.info("player not invincible");
        // game over
        emenyTimer.stop();
        removeKeyListener(keyListener);
        g.setFont(new Font(null, Font.PLAIN, 69));
        g.setColor(Color.ORANGE);
        g.drawString("Mission Failed", 50, 200);
      }
    }
    if (floorStates.noMoreTokens()) {
      emenyTimer.stop();
      g.setFont(new Font(null, Font.PLAIN, 69));
      g.setColor(Color.GREEN);
      g.drawString("Mission Accomplished", 10, 200);
    }
    requestFocusInWindow(); // NOTE: This enables KeyListener on JPanel. This has to be called after JFrame is set to visible
  }

  /**
   * place static tiles such as walls, gem and tokens
   *
   * @param g
   */
  private void paintTiles(Graphics2D g) {
    final int maxRows = floorStates.getNumOfRows();
    final int maxCols = floorStates.getNumOfCols();

    for (int row = 0; row < maxRows; row++) {
      for (int col = 0; col < maxCols; col++) {
        fillRect(g, row, col, Color.PINK);
        switch (floorStates.getTileType(row, col)) {
          case W:
            fillRect(g, row, col, Color.BLACK);
            break;
          case G:
            if (floorStates.hasGemAt(Location.of(row, col))) {
              fillDiamond(g, row, col, Color.MAGENTA);
            }
            break;
          case T:
            if (floorStates.hasTokenAt(Location.of(row, col))) {
              fillOval(g, row, col, Color.YELLOW);
            }
            break;
        }
      }
    }
  }

  private void paintPlayer(Graphics2D g) {
    Color color = invincible.get() ? Color.BLUE : Color.CYAN;
    fillOval(g, currentPlayerLocation.row, currentPlayerLocation.col, color);
  }

  private void paintEnemies(Graphics2D g) {
    floorStates.getEnemyLocations().forEach(location -> fillPolygon(g, location.row, location.col, Color.RED));
  }

  private static void fillRect(Graphics2D g, int row, int col, Color color) {
    g.setColor(color);
    g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
  }

  private static void fillOval(Graphics2D g, int row, int col, Color color) {
    g.setColor(color);
    g.fillOval(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
  }

  private static void fillPolygon(Graphics2D g, int row, int col, Color color) {
    int delta = TILE_SIZE / 2;
    g.setColor(color);
    g.fillPolygon(new StarPolygon(col * TILE_SIZE + delta, row * TILE_SIZE + delta, delta, delta / 2));
  }

  private static void fillDiamond(Graphics2D g, int row, int col, Color color) {
    g.setColor(color);
    int delta = TILE_SIZE / 2;
    g.fillPolygon(
        new int[]{
            col * TILE_SIZE + delta,
            col * TILE_SIZE + TILE_SIZE,
            col * TILE_SIZE + delta,
            col * TILE_SIZE
        },
        new int[]{
            row * TILE_SIZE,
            row * TILE_SIZE + delta,
            row * TILE_SIZE + TILE_SIZE,
            row * TILE_SIZE + delta
        },
        4);
  }

  /**
   * @param directionCode
   * @param location
   * @return destination {@link Location} or location
   */
  private Location getTargetLocation(int directionCode, Location location) {
    switch (directionCode) {
      case KeyEvent.VK_UP:
        return floorStates.getNorthLocation(location);
      case KeyEvent.VK_DOWN:
        return floorStates.getSouthLocation(location);
      case KeyEvent.VK_LEFT:
        return floorStates.getWestLocation(location);
      case KeyEvent.VK_RIGHT:
        return floorStates.getEastLocation(location);
      default:
        return location;
    }
  }

  private class MyKeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {
      Location destination = getTargetLocation(event.getKeyCode(), currentPlayerLocation);
      if (destination.equals(currentPlayerLocation)) {
        return;
      }
      currentPlayerLocation = destination;
      switch (floorStates.getTileType(currentPlayerLocation)) {
        case D:
          floorStates.goDown();
          break;
        case U:
          floorStates.goUp();
          break;
      }
      floorStates.removeTokenAt(currentPlayerLocation);
      if (floorStates.removeGemAt(currentPlayerLocation)) {
        // gives the player invincible power
        invincible.set(true);
        invincibleTimer.restart();
      }
      repaint();

    }
  }

  private class InvincibleMode implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      invincible.set(false);
      invincibleTimer.stop();
    }
  }

  private class AnimateEnemy implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      log.info("Enemy turn");
      Set<Location> destinations = Sets.newHashSet();
      // Move locations of enemies
      for (Location location : floorStates.getEnemyLocations()) {
        Location destination = getTargetLocation(
            DIRECTIONS[random.nextInt(DIRECTIONS.length)], location);
        if (destinations.contains(destination) || floorStates.isEnemyAt(destination)) {
          // Enemy cannot move to a location another enemy is already on.
          destinations.add(location);
        } else {
          destinations.add(destination);
        }
      }
      floorStates.refreshEnemyLocations(destinations);
      repaint();
    }
  }

}
