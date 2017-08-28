package com.djd.fun.tachchapter.demo014swing.maze;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.djd.fun.tachchapter.demo014swing.canvas.Abstract2DPanel;
import com.djd.fun.tachchapter.demo014swing.maze.models.Location;
import com.djd.fun.tachchapter.demo014swing.maze.models.MoreColors;
import com.djd.fun.tachchapter.demo014swing.maze.models.Tile;
import com.djd.fun.tachchapter.demo014swing.maze.shapes.ShapeHelper;
import com.djd.fun.tachchapter.demo014swing.maze.states.FloorStates;
import com.google.common.collect.Sets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.djd.fun.tachchapter.demo014swing.maze.shapes.ShapeHelper.paintGem;
import static com.djd.fun.tachchapter.demo014swing.maze.shapes.ShapeHelper.paintEnemy;
import static com.djd.fun.tachchapter.demo014swing.maze.shapes.ShapeHelper.paintWall;
import static com.djd.fun.tachchapter.demo014swing.maze.shapes.ShapeHelper.paintToken;
import static com.djd.fun.tachchapter.demo014swing.maze.shapes.ShapeHelper.paintDownstairs;
import static com.djd.fun.tachchapter.demo014swing.maze.shapes.ShapeHelper.paintUpstairs;

public class SmallGame extends Abstract2DPanel {

  private static final Logger log = LoggerFactory.getLogger(SmallGame.class);
  private static final int[] DIRECTIONS = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
  private static final int TILE_SIZE = 21; // Note Use even number to avoid loss of decimal

  private final KeyListener keyListener;
  private final ActionListener animateEnemy;
  private final ActionListener invincibleListener;
  private final Random random;
  private final Timer invincibleTimer;
  private final Timer emenyTimer;
  private final AtomicBoolean invincible;
  private FloorStates floorStates;
  private Location currentPlayerLocation;

  public SmallGame() {
    this.keyListener = new MyKeyListener();
    this.animateEnemy = new AnimateEnemy();
    this.invincibleListener = new InvincibleMode();
    this.random = new Random(0);
    this.invincible = new AtomicBoolean();
    this.invincibleTimer = new Timer(5000, invincibleListener);
    this.emenyTimer = new Timer(777, animateEnemy);
  }

  @Override
  public void init() {
    this.floorStates = new FloorStates();
    this.currentPlayerLocation = floorStates.getOriginalPlayerLocation();
    this.invincibleTimer.setRepeats(false);
    this.emenyTimer.restart();
    removeKeyListener(keyListener); // Just making sure
    addKeyListener(keyListener);
  }

  @Override
  protected void paintComponent(Graphics2D g) {
    paintTiles(g);
    paintPlayer(g);
    paintEnemies(g);
    requestFocusInWindow(); // NOTE: This enables KeyListener on JPanel. This has to be called after JFrame is set to visible
  }

  private void checkGameStatus() {
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
        showDialog("Mission Failed");
      }
    }
    if (floorStates.noMoreTokens()) {
      emenyTimer.stop();
      removeKeyListener(keyListener);
      showDialog("Mission Accomplished");
    }
  }

  private void showDialog(String message) {
    int n = JOptionPane.showConfirmDialog(this, message + " Reinitialize the Board?");
    if (n == JOptionPane.YES_OPTION) {
      init();
    }
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
        Tile.TileType tileType = floorStates.getTileType(row, col);
        if (tileType != Tile.TileType.W) {
          // This is technically not a wall but back ground tile or hollow tile
          paintWall(g, row, col, MoreColors.LIGHT.GREEN, TILE_SIZE);
        }
        switch (tileType) {
          case W:
            paintWall(g, row, col, MoreColors.DARK.GREEN, TILE_SIZE);
            break;
          case T:
            if (floorStates.hasTokenAt(Location.of(row, col))) {
              paintToken(g, row, col, MoreColors.LIGHT.BLUE, TILE_SIZE);
            }
            break;
          case G:
            if (floorStates.hasGemAt(Location.of(row, col))) {
              paintGem(g, row, col, MoreColors.DARK.VIOLET, TILE_SIZE);
            }
            break;
          case D:
            paintDownstairs(g, row, col, MoreColors.DARK.BROWN, TILE_SIZE);
            break;
          case U:
            paintUpstairs(g, row, col, MoreColors.DARK.PURPLE, TILE_SIZE);
            break;
        }
      }
    }
  }

  private void paintPlayer(Graphics2D g) {
    Color color = invincible.get() ? MoreColors.LIGHT.YELLOW : MoreColors.NEON.BLUE;
    ShapeHelper.paintPlayer(g, currentPlayerLocation.row, currentPlayerLocation.col, color, TILE_SIZE);
  }

  private void paintEnemies(Graphics2D g) {
    floorStates.getEnemyLocations()
        .forEach(location -> paintEnemy(g, location.row, location.col, Color.RED, TILE_SIZE));
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
      log.info("Key event: {}", event);
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
      checkGameStatus();

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
      checkGameStatus();
    }
  }
}
