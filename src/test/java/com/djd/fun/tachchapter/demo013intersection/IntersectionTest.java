package com.djd.fun.tachchapter.demo013intersection;

import java.util.List;

import com.google.common.collect.ImmutableList;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author JGD
 * @since 11/28/16
 */
public class IntersectionTest {
  @Test
  public void findIntersection_bothEmpty() {
    List<String> list1 = ImmutableList.of();
    List<String> list2 = ImmutableList.of();
    assertThat(Intersection.findIntersection(list1, list2)).containsExactly();
  }

  @Test
  public void findIntersection_list1Empty() {
    List<String> list1 = ImmutableList.of();
    List<String> list2 = ImmutableList.of("abc");
    assertThat(Intersection.findIntersection(list1, list2)).containsExactly();
  }

  @Test
  public void findIntersection_list2Empty() {
    List<String> list1 = ImmutableList.of("abc");
    List<String> list2 = ImmutableList.of();
    assertThat(Intersection.findIntersection(list1, list2)).containsExactly();
  }

  @Test
  public void findIntersection_bothNonEmpty() {
    List<String> list1 = ImmutableList.of("abc");
    List<String> list2 = ImmutableList.of("xyz");
    assertThat(Intersection.findIntersection(list1, list2)).containsExactly();
  }

  @Test
  public void findIntersection_bothNonEmpty_xyz() {
    List<String> list1 = ImmutableList.of("abc", "xyz");
    List<String> list2 = ImmutableList.of("xyz");
    assertThat(Intersection.findIntersection(list1, list2)).containsExactly("xyz");
  }

  @Test
  public void findIntersection_duplicatesInList1() {
    List<String> list1 = ImmutableList.of("abc", "xyz", "abc");
    List<String> list2 = ImmutableList.of("xyz", "abc");
    assertThat(Intersection.findIntersection(list1, list2)).containsExactly("abc", "xyz");
  }

  @Test
  public void findIntersection_duplicatesInList2() {
    List<String> list1 = ImmutableList.of("abc", "xyz");
    List<String> list2 = ImmutableList.of("xyz", "abc", "abc");
    assertThat(Intersection.findIntersection(list1, list2)).containsExactly("abc", "xyz");
  }

}