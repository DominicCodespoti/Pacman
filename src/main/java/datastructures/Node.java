package datastructures;

import world.Point;

public class Node<T> {

  public Point position;
  public T value;
  Node up, down, left, right;

  Node() {
    up = down = left = right = null;
  }
}