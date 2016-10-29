package com.djd.fun.tachchapter.demo011reverse;

import java.util.Objects;

import com.sun.istack.internal.Nullable;

/**
 * @author JGD
 * @since 10/23/16
 */
public class ReverseSinglyLinkedList {
  /**
   *
   * @param head of the linked list
   * @return head of reversed linked list
   */
  public Node reverse(Node head) {
    if (head == null || head.next == null) {
      return head;
    }
    Node prevNode = null;
    Node currNode = head;
    while(currNode != null) {
      Node nextNode = currNode.next;
      currNode.next = prevNode;
      prevNode = currNode;
      currNode = nextNode;
    }
    return prevNode;
  }

  public static class Node {
    public final int value;
    public @Nullable Node next;

    public Node(int value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Node node = (Node)o;
      return value == node.value &&
          Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value, next);
    }
  }
}
