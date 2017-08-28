package com.djd.fun.tachchapter.demo014swing.maze.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ShapeHelperTest extends Mockito {

  private @Mock Graphics2D g;

  @Test
  public void paintWall() {
    ShapeHelper.paintWall(g, 2, 3, Color.PINK, 20);
    verify(g).setColor(Color.PINK);
    verify(g).fillRect(60, 40, 20, 20);
  }

  @Test
  public void paintGem() {
    ShapeHelper.paintGem(g, 2, 3, Color.PINK, 20);
    verify(g).setColor(Color.PINK);
    verify(g).fill(Diamond.with(2, 3, 20));
  }

  @Test
  public void paintPlayer() {
    ShapeHelper.paintPlayer(g, 2, 3, Color.PINK, 20);
    verify(g).setColor(Color.PINK);
    verify(g).fillOval(60, 40, 20, 20);
  }

  @Test
  public void paintEnemy() {
    ShapeHelper.paintEnemy(g, 2, 3, Color.PINK, 20);
    verify(g).setColor(Color.PINK);
    verify(g).fill(StarPolygon.with(2, 3, 20));
  }

  @Test
  public void paintToken() {
    ShapeHelper.paintToken(g, 2, 3, Color.PINK, 20);
    verify(g).setColor(Color.PINK);
    verify(g).fillOval(64, 44, 12, 12);
  }

  @Test
  public void paintUpstairs() {
    ShapeHelper.paintUpstairs(g, 2, 3, Color.PINK, 20);
    verify(g).drawRect(62, 42, 16, 16);
    verify(g).setColor(Color.PINK);
    verify(g).fill(Triangle.up(2, 3, 20));
  }

  @Test
  public void paintDownstairs() {
    ShapeHelper.paintDownstairs(g, 2, 3, Color.PINK, 20);
    verify(g).drawRect(62, 42, 16, 16);
    verify(g).setColor(Color.PINK);
    verify(g).fill(Triangle.down(2, 3, 20));
  }

//  @Test(expected = IllegalArgumentException.class)
  @Test
  public void paintEnemy_sizeTooSmall() {
    ShapeHelper.paintEnemy(g, 2, 3, Color.PINK, 1);
  }
}