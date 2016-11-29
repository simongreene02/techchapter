package com.djd.fun.tachchapter.demo013intersection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * @author JGD
 * @since 11/28/16
 */
public class Intersection {

  /**
   * @param list1
   * @param list2
   * @return intersection of list1 and list2
   * @throws NullPointerException if either list is null.
   */
  public static List<String> findIntersection(List<String> list1, List<String> list2) {
    if (list1 == null || list2 == null) {
      throw new NullPointerException("Both lists must be non-null");
    }
    Set<String> target = new HashSet(list1);
    Predicate<String> predicate = str -> target.contains(str);
    return list2.stream()
        .distinct()
        .filter(predicate)
        .collect(Collectors.toList());
  }
}
