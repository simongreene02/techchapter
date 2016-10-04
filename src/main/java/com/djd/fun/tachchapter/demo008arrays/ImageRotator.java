package com.djd.fun.tachchapter.demo008arrays;

/**
 * @author JGD
 * @since 10/2/16
 */
public class ImageRotator {

  /**
   * Rotate image in 90 degree
   * <pre>
   * x x x x
   * w w w w
   * y y y y
   * z z z z
   * </pre>
   * becomes
   * <pre>
   *   z y w x
   *   z y w x
   *   z y w x
   *   z y w x
   * </pre>
   *
   * @param image
   */
  public static int[][] rotate90(int[][] image) {
    if (image == null || image.length == 0) {
      throw new IllegalArgumentException("image should be at least 1x1");
    }
    int n = image.length;
    int[][] result = new int[n][n];
    for (int row = 0; row < n; row++) {
      int destColIdx = n - 1 - row;
      for (int col = 0; col < n; col++) {
        int destRowIdx = col;
        result[destRowIdx][destColIdx] = image[row][col];
      }
    }
    return result;
  }

  /**
   * In place rotation
   *
   * @param image
   */
  public static void rotateInplace90(int[][] image) {
    if (image == null || image.length == 0) {
      throw new IllegalArgumentException("image should be at least 1x1");
    }
    int n = image.length;
    for (int i = 0; i < n / 2; i++) {
      for (int j = i; j < n - 1 - i; j++) {
        int tmp = image[i][j];
        // left to top
        image[i][j] = image[n - 1 - j][i];
        // bottom to left
        image[n - 1 - j][i] = image[n - 1 - i][n - 1 - j];
        // right to bottom
        image[n - 1 - i][n - 1 - j] = image[j][n - 1 - i];
        // top to right
        image[j][n - 1 - i] = tmp;
      }
    }
  }
}
