package com.djd.fun.tachchapter.demo002;

import java.util.ArrayList;

/**
 * Java does not allow to create an array of parameterized type
 */
public class Demo002 {

  public static final void main(String [] params) {
//  ArrayList<String>[] strings = new ArrayList<String[1]; // Compile error
    ArrayList<String>[] strings = new ArrayList[1];
  }
}
