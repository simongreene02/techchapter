package com.djd.fun.tachchapter.demo014swing.maze.models;

import java.util.Arrays;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TileTest {

  @Test
  public void init_validTypes() {
    Arrays.stream(Tile.TileType.values())
        .map(Tile.TileType::toString)
        .map(name -> name.charAt(0))
        .map(letter -> Tile.of(0, 0, letter))
        .forEach(tile -> assertThat(tile.toString()).hasLength(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void init_invalidType() {
    Tile.of(0, 0, 'Z');
  }

  @Test(expected = IllegalArgumentException.class)
  public void init_invalidX() {
    Tile.of(-1, 0, Tile.TileType.G.getPrintableLetter());
  }

  @Test(expected = IllegalArgumentException.class)
  public void init_invalidY() {
    Tile.of(0, -1, Tile.TileType.G.getPrintableLetter());
  }

  @Test
  public void getTileType() {
    Tile tile = Tile.of(0, 0, Tile.TileType.G.getPrintableLetter());
    assertThat(tile.getTileType()).isEqualTo(Tile.TileType.G);
  }

  @Test
  public void isSteppable_W_false() {
    assertFalse(Tile.of(0, 0, Tile.TileType.W.getPrintableLetter()).isSteppable());
  }

  @Test
  public void isSteppable_H_true() {
    assertTrue(Tile.of(0, 0, 'H').isSteppable());
  }

  @Test
  public void isSteppable_U_true() {
    assertTrue(Tile.of(0, 0, Tile.TileType.U.getPrintableLetter()).isSteppable());
  }

  @Test
  public void isSteppable_D_true() {
    assertTrue(Tile.of(0, 0, Tile.TileType.D.getPrintableLetter()).isSteppable());
  }

  @Test
  public void isSteppable_T_true() {
    assertTrue(Tile.of(0, 0, Tile.TileType.T.getPrintableLetter()).isSteppable());
  }

  @Test
  public void isSteppable_G_true() {
    assertTrue(Tile.of(0, 0, Tile.TileType.G.getPrintableLetter()).isSteppable());
  }

  @Test
  public void isSteppable_E_true() {
    assertTrue(Tile.of(0, 0, Tile.TileType.E.getPrintableLetter()).isSteppable());
  }

  @Test
  public void isSteppable_P_true() {
    assertTrue(Tile.of(0, 0, Tile.TileType.P.getPrintableLetter()).isSteppable());
  }
}