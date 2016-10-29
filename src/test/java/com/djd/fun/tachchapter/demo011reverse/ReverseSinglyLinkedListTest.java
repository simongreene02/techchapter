package com.djd.fun.tachchapter.demo011reverse;

import com.djd.fun.tachchapter.demo011reverse.ReverseSinglyLinkedList.Node;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author JGD
 * @since 10/23/16
 */
public class ReverseSinglyLinkedListTest {

  private ReverseSinglyLinkedList reverseSinglyLinkedList;

  @Before
  public void setUp() {
    reverseSinglyLinkedList = new ReverseSinglyLinkedList();
  }

  @Test
  public void reverse_nullHead() {
    assertThat(reverseSinglyLinkedList.reverse(null)).isNull();
  }

  @Test
  public void reverse_oneNode() {
    Node head = new Node(1);
    assertThat(reverseSinglyLinkedList.reverse(head)).isEqualTo(head);
  }

  @Test
  public void reverse_twoNodes() {
    Node result = reverseSinglyLinkedList.reverse(createList(new int[]{1, 2}));
    assertThat(result).isEqualTo(createList(new int[]{2, 1}));
  }

  @Test
  public void reverse_sevenNodes() {
    Node result = reverseSinglyLinkedList.reverse(createList(new int[]{1, 2, 3, 4, 5, 6, 7}));
    assertThat(result).isEqualTo(createList(new int[]{7, 6, 5, 4, 3, 2, 1}));
    print(result);
  }

  /**
   * Helper method to create a linked list from given int array
   * @param items in int array
   * @return haed {@link Node} of the list
   */
  private static Node createList(int[] items) {
    Node head = null;
    Node curr = null;
    for (int item : items) {
      Node node = new Node(item);
      if (curr == null) {
        curr = node;
        head = node;
      } else {
        curr.next = node;
        curr = node;
      }
    }
    return head;
  }

  /**
   * debug method to print linked list values.
   * @param head
   */
  private static void print(Node head) {
    Node curr = head;
    while (curr != null) {
      System.out.println(curr.value);
      curr = curr.next;
    }
  }
}