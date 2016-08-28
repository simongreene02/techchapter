package com.djd.fun.tachchapter.demo006;

import java.util.List;

import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class demos jdk8 lambda
 *
 * @author JGD
 * @since 8/28/16
 */
public class DaLambda {
  private static final Logger log = LoggerFactory.getLogger(DaLambda.class);

  public static void main(String[] params) {
    List<String> mutableList = Lists.newArrayList("Cherry", "Orange", "Apple");
    log.info("Original list: {}", mutableList);
    mutableList.sort((a, b) -> a.compareTo(b)); // sort elements in ascending order
    log.info("Ascending order list: {}", mutableList);
    mutableList.sort((a, b) -> b.compareTo(a)); // sort elements in descending order
    log.info("Ascending order list: {}", mutableList);
  }
}
