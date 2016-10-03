package com.djd.fun.tachchapter.demo008arrays;

import java.util.Arrays;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;


/**
 * @author JGD
 * @since 10/2/16
 */
public class ImageRotatorTest {
  @Test
  public void rotate90() {
    int[][] image = new int[][]{
        {1, 2, 3, 4},
        {1, 2, 3, 4},
        {1, 2, 3, 4},
        {1, 2, 3, 4},
    };
    int[][] expected = new int[][]{
        {1, 1, 1, 1},
        {2, 2, 2, 2},
        {3, 3, 3, 3},
        {4, 4, 4, 4},
    };
    int[][] output = ImageRotator.rotate90(image);
    print(output);
    assertThat(Arrays.deepEquals(output, expected)).isTrue();
  }

  private static void print(int[][] image) {
    Arrays.stream(image)
        .forEach(row -> {
          Arrays.stream(row).forEach(System.out::print);
          System.out.println();
        });
  }
}