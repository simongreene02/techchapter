package com.djd.fun.tachchapter.demo008arrays;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;


/**
 * @author JGD
 * @since 10/2/16
 */
public class ImageRotatorTest {
  private int[][] image;
  private int[][] expected;

  @Before
  public void setUp() {
    image = new int[][]{
        {1, 2, 3, 4},
        {1, 2, 3, 4},
        {1, 2, 3, 4},
        {1, 2, 3, 4}
    };
    expected = new int[][]{
        {1, 1, 1, 1},
        {2, 2, 2, 2},
        {3, 3, 3, 3},
        {4, 4, 4, 4}
    };
  }

  @Test
  public void rotate90() {
    int[][] output = ImageRotator.rotate90(image);
    print(output);
    assertThat(Arrays.deepEquals(output, expected)).isTrue();
  }

  @Test
  public void rotateInplace90() {
    print(image);
    ImageRotator.rotateInplace90(image);
    print(image);
    assertThat(Arrays.deepEquals(image, expected)).isTrue();
  }

  @Test(expected = IllegalArgumentException.class)
  public void rotateInplace90_nullImage_error() {
    ImageRotator.rotateInplace90(null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void rotateInplace90_emptyImage_error() {
    ImageRotator.rotateInplace90(new int[0][0]);
  }

  @Test
  public void rotateInplace90_1x1Image() {
    int[][] payload = new int[1][1];
    ImageRotator.rotateInplace90(new int[1][1]);
    assertThat(Arrays.deepEquals(payload, new int[1][1])).isTrue();
  }

  @Test
  public void rotateInplace90_2x2Image() {
    int[][] payload = new int[][]{
        {1, 2},
        {3, 4}
    };
    ImageRotator.rotateInplace90(payload);
    assertThat(Arrays.deepEquals(payload, new int[][]{
        {3, 1},
        {4, 2}
    })).isTrue();
  }

  @Test
  public void rotateInplace90_3x3Image() {
    int[][] payload = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    ImageRotator.rotateInplace90(payload);
    assertThat(Arrays.deepEquals(payload, new int[][]{
        {7, 4, 1},
        {8, 5, 2},
        {9, 6, 3}
    })).isTrue();
  }

  private static void print(int[][] image) {
    Arrays.stream(image)
        .forEach(row -> {
          Arrays.stream(row).forEach(System.out::print);
          System.out.println();
        });
  }
}